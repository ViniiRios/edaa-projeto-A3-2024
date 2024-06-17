document.addEventListener('DOMContentLoaded', function() {
    listarFerramentas();
});

 async function listarFerramentas() {
  const response = await fetch('/api/ferramentas')
        .then(response => response.json())
        .then(ferramentas => {
            const lista = document.getElementById('ferramentas-list');
            lista.innerHTML = '';

            ferramentas.forEach(ferramenta => {
                const item = document.createElement('li');
                item.textContent = `${ferramenta.nome} (ID: ${ferramenta.id}) - ${ferramenta.disponivel ? 'Disponível' : 'Emprestada'}`;
                lista.appendChild(item);
            });
        });
}

async function emprestarFerramenta() {
    const id = document.getElementById('emprestar-id').value;
    const usuarioId = document.getElementById('emprestar-usuario-id').value;
    const usuarioNome = document.getElementById('emprestar-usuario-nome').value;

  const response = await fetch(`/api/ferramentas/emprestar?id=${id}&usuarioId=${usuarioId}&usuarioNome=${usuarioNome}`, { method: 'POST' })
        .then(response => {
            if (response.ok) {
                alert('Ferramenta emprestada com sucesso!');
                listarFerramentas();
            } else {
                response.text().then(text => alert(text));
            }
        });
}

async function darBaixaFerramenta() {
    const id = document.getElementById('devolver-id').value;

 const response = await fetch(`/api/ferramentas/devolver?id=${id}`, { method: 'POST' })
        .then(response => {
            if (response.ok) {
                alert('Ferramenta devolvida com sucesso!');
                listarFerramentas();
            } else {
                response.text().then(text => alert(text));
            }
        });
}











