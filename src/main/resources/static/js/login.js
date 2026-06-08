function mostrarAlerta(mensagem, tipo) {
    const alerta = document.getElementById("alerta");
    alerta.textContent = mensagem;
    alerta.style.display = "block";
    alerta.style.backgroundColor = tipo === "erro" ? "#ffe0e0" : "#e0ffe8";
    alerta.style.color = tipo === "erro" ? "#c0392b" : "#27ae60";
    alerta.style.border = "1px solid " + (tipo === "erro" ? "#c0392b" : "#27ae60");
}

const params = new URLSearchParams(window.location.search);
if (params.get("erro") === "true") {
    mostrarAlerta("Email ou senha incorretos. Tente novamente.", "erro");
}

document.getElementById("formLogin").addEventListener("submit", function(e) {
    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value.trim();

    if (!email || !senha) {
        e.preventDefault();
        mostrarAlerta("Preencha todos os campos antes de continuar.", "erro");
        return;
    }

    mostrarAlerta("Entrando... 💜", "sucesso");
});