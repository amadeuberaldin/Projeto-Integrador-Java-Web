// Função para mostrar os detalhes de um produto
function showProduto(id) {
    fetch(`/produtos/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao buscar o produto');
                }
                return response.json();
            })
            .then(data => {
                document.querySelector(".col-produto-info").innerHTML = `
                <h2>Informações do Produto</h2>
                <p>Nome: ${data.nome}</p>
                <p>Porção de Referência: ${data.porcaoReferencia}</p>
                <p>Proteínas: ${data.proteinas}</p>
                <p>Carboidratos: ${data.carboidratos}</p>
                <p>Calorias: ${data.calorias}</p>
                <p>Gorduras Totais: ${data.gordurasTotais}</p>
                <button onclick="editProduto(${data.id})">Editar</button>
                <button onclick="deleteProduto(${data.id})">Excluir</button>
            `;
            })
            .catch(error => {
                console.error(error);
                alert("Erro ao carregar o produto.");
            });
}

// Função para mostrar os detalhes de uma receita
function showReceita(id) {
    fetch(`/receitas/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao buscar a receita');
                }
                return response.json();
            })
            .then(data => {
                let ingredientes = '';
                data.ingredientes.forEach(ing => {
                    ingredientes += `<li>${ing.produto.nome}: ${ing.quantidade}</li>`;
                });

                document.querySelector(".col-receita-info").innerHTML = `
                <h2>Informações da Receita</h2>
                <p>Nome: ${data.nome}</p>
                <p>Ingredientes:</p>
                <ul>${ingredientes}</ul>
                <p>Modo de Preparo: ${data.modoPreparo}</p>
                <button onclick="editReceita(${data.id})">Editar</button>
                <button onclick="deleteReceita(${data.id})">Excluir</button>
            `;
            })
            .catch(error => {
                console.error(error);
                alert("Erro ao carregar a receita.");
            });
}

// Função genérica para excluir um item (produto ou receita)
async function deleteItem(type, id) {
    if (confirm(`Tem certeza que deseja excluir este ${type}?`)) {
        try {
            const response = await fetch(`/${type}/deletar/${id}`, {method: 'DELETE'});
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
