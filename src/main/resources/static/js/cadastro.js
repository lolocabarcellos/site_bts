function mostrarAlerta(mensagem, tipo) {
    const alerta = document.getElementById("alerta");
    alerta.textContent = mensagem;
    alerta.style.display = "block";
    alerta.style.backgroundColor = tipo === "erro" ? "#ffe0e0" : "#e0ffe8";
    alerta.style.color = tipo === "erro" ? "#c0392b" : "#27ae60";
    alerta.style.border = "1px solid " + (tipo === "erro" ? "#c0392b" : "#27ae60");
}

document.getElementById("formCadastro").addEventListener("submit", function(e) {
    const nome  = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value.trim();

    if (!nome || !email || !senha) {
        e.preventDefault();
        mostrarAlerta("Preencha todos os campos antes de continuar.", "erro");
        return;
    }

    if (senha.length < 6) {
        e.preventDefault();
        mostrarAlerta("A senha deve ter pelo menos 6 caracteres.", "erro");
        return;
    }

    mostrarAlerta("Cadastrando... 💜", "sucesso");
});