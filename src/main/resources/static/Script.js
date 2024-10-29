// Função para mostrar os detalhes de um produto e adicionar a opção de editar e excluir
function showProduto(id) {
    fetch(`/produtos/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('produto-detalhes').innerHTML = `
                <p>Nome: ${data.nome}</p>
                <p>Porção de Referência: ${data.porcaoReferencia}</p>
                <p>Proteínas: ${data.proteinas}</p>
                <p>Carboidratos: ${data.carboidratos}</p>
                <p>Calorias: ${data.calorias}</p>
                <p>Gorduras Totais: ${data.gordurasTotais}</p>
                <button onclick="openEditPopup(${data.id})">Editar</button>
                <button onclick="deleteProduto(${data.id})" style="margin-left: 10px;">Excluir</button>
            `;
            })
            .catch(error => console.error("Erro ao buscar o produto:", error));
}

// Função para abrir o pop-up de edição e preencher os campos com os dados do produto
function openEditPopup(id) {
    fetch(`/produtos/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('edit-nome').value = data.nome;
                document.getElementById('edit-porcao').value = data.porcaoReferencia;
                document.getElementById('edit-proteinas').value = data.proteinas;
                document.getElementById('edit-carboidratos').value = data.carboidratos;
                document.getElementById('edit-calorias').value = data.calorias;
                document.getElementById('edit-gorduras').value = data.gordurasTotais;
                document.getElementById('edit-popup').style.display = 'block';
                document.getElementById('edit-popup').dataset.productId = id;
            })
            .catch(error => console.error("Erro ao abrir o pop-up de edição:", error));
}

// Função para salvar as edições do produto
async function saveEdits() {
    const id = document.getElementById('edit-popup').dataset.productId;
    const updatedProduct = {
        nome: document.getElementById('edit-nome').value,
        porcaoReferencia: parseFloat(document.getElementById('edit-porcao').value),
        proteinas: parseFloat(document.getElementById('edit-proteinas').value),
        carboidratos: parseFloat(document.getElementById('edit-carboidratos').value),
        calorias: parseFloat(document.getElementById('edit-calorias').value),
        gordurasTotais: parseFloat(document.getElementById('edit-gorduras').value)
    };

    // Obtém o token CSRF dos meta tags
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    try {
        const response = await fetch(`/produtos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken // Insere o token CSRF
            },
            body: JSON.stringify(updatedProduct)
        });

        if (response.ok) {
            alert("Produto atualizado com sucesso.");
            closeEditPopup();
            window.location.reload();
        } else {
            const errorMessage = await response.text();
            throw new Error(errorMessage || "Erro ao atualizar o produto.");
        }
    } catch (error) {
        alert("Erro ao salvar as alterações: " + error.message);
    }
}

// Função para fechar o pop-up de edição
function closeEditPopup() {
    document.getElementById('edit-popup').style.display = 'none';
}

// Função para mostrar os detalhes de uma receita, simplificada
function showReceita(id) {
    fetch(`/receitas/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                let ingredientes = '';

                // Adicionar ingredientes com suas quantidades
                data.ingredientes.forEach(ing => {
                    ingredientes += `<li>${ing.produtoNome}: ${ing.quantidade}g</li>`;
                });

                // Exibir as informações da receita no formato desejado
                document.querySelector("#receita-detalhes").innerHTML = `
                <h3>Informações da Receita</h3>
                <p><strong>Nome:</strong> ${data.nome}</p>
                <p><strong>Ingredientes:</strong></p>
                <ul>${ingredientes}</ul>
                <p><strong>Modo de Preparo:</strong></p>
                <p>${data.modoPreparo}</p>
                <button onclick="deleteReceita(${data.id})">Excluir</button>
            `;
            })
            .catch(error => console.error("Erro ao carregar receita:", error));
}




// Função para abrir o popup de edição de receita
function editReceita(id) {
    fetch(`/receitas/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('edit-nome-receita').value = data.nome;
                document.getElementById('edit-modo-preparo').value = data.modoPreparo;
                const ingredientesContainer = document.getElementById('edit-ingredientes-container');
                ingredientesContainer.innerHTML = ''; // Limpa os ingredientes anteriores

                data.ingredientes.forEach((ing, index) => {
                    const template = document.getElementById('ingrediente-template').innerHTML;
                    const newIngredientHTML = template
                            .replace(/INDEX/g, index)
                            .replace(/{{produtoId}}/g, ing.produto.id)
                            .replace(/{{quantidade}}/g, ing.quantidade);
                    ingredientesContainer.insertAdjacentHTML('beforeend', newIngredientHTML);
                });

                document.getElementById('edit-popup').style.display = 'block';
                document.getElementById('edit-popup').dataset.receitaId = id;
            })
            .catch(error => console.error("Erro ao abrir o popup de edição:", error));
}

// Função para salvar as edições da receita
async function saveReceitaEdits() {
    const id = document.getElementById('edit-popup').dataset.receitaId;
    const nomeReceita = document.getElementById('edit-nome-receita').value;
    const modoPreparo = document.getElementById('edit-modo-preparo').value;

    // Mapear os ingredientes e filtrar os incompletos
    const ingredientes = Array.from(document.querySelectorAll('.edit-ingrediente-row')).map(row => ({
            produto: {id: row.querySelector('.produto-select').value},
            quantidade: row.querySelector('input[type="number"]').value
        })).filter(ing => ing.produto.id && ing.quantidade);

    const receitaData = {
        nome: nomeReceita,
        modoPreparo: modoPreparo,
        ingredientes: ingredientes
    };

    try {
        const response = await fetch(`/receitas/${id}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(receitaData)
        });

        if (response.ok) {
            alert("Receita atualizada com sucesso!");
            closeEditPopup();
            window.location.reload();
        } else {
            throw new Error("Erro ao atualizar a receita.");
        }
    } catch (error) {
        alert("Erro ao salvar as alterações: " + error.message);
    }
}

// Função para fechar o popup de edição
function closeEditPopup() {
    document.getElementById('edit-popup').style.display = 'none';
}

// Função para excluir um produto
async function deleteItem(type, id) {
    if (confirm(`Tem certeza que deseja excluir este ${type}?`)) {
        try {
            // Obtém o token CSRF dos meta tags
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

            // Atualiza a URL para usar o caminho correto
            const response = await fetch(`/${type}s/${id}`, {// 's' após type para pluralidade (produtos ou receitas)
                method: 'DELETE',
                headers: {
                    [csrfHeader]: csrfToken // Insere o token CSRF no cabeçalho
                }
            });

            if (response.ok) {
                alert(`${type.charAt(0).toUpperCase() + type.slice(1)} excluído(a) com sucesso.`);
                window.location.reload();
            } else {
                const errorMessage = await response.text();
                throw new Error(errorMessage || `Erro ao excluir o ${type}`);
            }
        } catch (error) {
            alert(`Erro ao excluir ${type}: ${error.message}`);
        }
    }
}

// Funções específicas para excluir produtos e receitas usando deleteItem
function deleteProduto(id) {
    deleteItem('produto', id);
}

function deleteReceita(id) {
    deleteItem('receita', id);
}

// Funções para redirecionar para as páginas de edição
function editProduto(id) {
    window.location.href = `/produtos/editar/${id}`;
}

function editReceita(id) {
    window.location.href = `/receitas/editar/${id}`;
}

// Verifica se o formulário existe antes de adicionar o listener
const form = document.querySelector("form");
if (form) {
    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Impede o envio padrão

        const formData = new FormData(this);
        fetch("/produtos/salvar", {
            method: "POST",
            body: formData
        })
                .then(response => {
                    if (!response.ok)
                        throw new Error("Erro ao salvar produto");
                    return response.json();
                })
                .then(produto => {
                    // Atualiza a lista na coluna 2
                    const listaProdutos = document.querySelector("#lista-produtos");
                    if (listaProdutos) {
                        const novoProduto = document.createElement("tr");
                        novoProduto.innerHTML = `<td onclick="showProduto(${produto.id})" style="cursor: pointer;">${produto.nome}</td>`;
                        listaProdutos.appendChild(novoProduto);
                    }
                })
                .catch(error => alert("Erro ao salvar o produto: " + error.message));
    });
}

let ingredienteIndex = 1;

function adicionarIngrediente() {
    const container = document.getElementById('ingredientes-container');
    const template = document.getElementById('ingrediente-template').innerHTML;

    // Substitui 'INDEX' pelo índice atual para garantir a exclusividade dos campos
    const newIngredientHTML = template.replace(/INDEX/g, ingredienteIndex);

    // Cria um novo elemento div e insere o HTML processado
    const newIngredientDiv = document.createElement('div');
    newIngredientDiv.innerHTML = newIngredientHTML;

    // Adiciona o novo ingrediente ao container
    container.appendChild(newIngredientDiv);

    ingredienteIndex++; // Incrementa o índice para o próximo ingrediente
}


async function submitRecipe() {
    const nomeReceita = document.getElementById('nomeReceita').value;
    const modoPreparo = document.getElementById('modoPreparo').value;

    // Mapear os ingredientes e filtrar os que estão incompletos
    const ingredientes = Array.from(document.querySelectorAll('.ingrediente-row'))
            .map(row => ({
                    produto: {id: row.querySelector('.produto-select').value},
                    quantidade: row.querySelector('input[type="number"]').value
                }))
            .filter(ingrediente => ingrediente.produto.id && ingrediente.quantidade); // Filtrar ingredientes com id e quantidade não vazios

    const receitaData = {
        nome: nomeReceita,
        modoPreparo: modoPreparo,
        ingredientes: ingredientes
    };

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    try {
        const response = await fetch('/receitas/salvar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(receitaData)
        });

        if (response.ok) {
            alert("Receita cadastrada com sucesso!");
            window.location.reload();
        } else {
            throw new Error("Erro ao cadastrar a receita.");
        }
    } catch (error) {
        alert("Erro ao cadastrar a receita: " + error.message);
    }
}