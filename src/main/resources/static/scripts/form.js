function validateForm() {
    var password = document.getElementById("password").value;
    var passwordConfirm = document.getElementById("password-confirm").value;
    var errorElement = document.getElementById("password-error");

    if (password !== passwordConfirm) {
    errorElement.textContent = "As senhas não correspondem.";
    return false;
}

    errorElement.textContent = "";
    return true;
}

function validateCompetences() {
    const checkboxes = document.querySelectorAll('input[name="necessarySkills"]');
    const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
    const errorElement = document.getElementById('competenceError');

    if (!isChecked) {
        errorElement.style.display = 'block';
        return false;
    } else {
        errorElement.style.display = 'none';
        return true;
    }
}

document.querySelector('form').onsubmit = validateCompetences;

document.querySelector('form').addEventListener('submit', function (event) {
    // Obter todas as competências selecionadas como "Necessárias"
    const necessarySkills = Array.from(document.querySelectorAll('input[name="necessarySkills"]:checked'))
        .map(input => input.value);

    // Obter todas as competências selecionadas como "Desejáveis"
    const desirableSkills = Array.from(document.querySelectorAll('input[name="desirableSkills"]:checked'))
        .map(input => input.value);

    // Verificar se há interseção entre as duas listas
    const duplicateSkills = necessarySkills.filter(skill => desirableSkills.includes(skill));

    // Se houver duplicatas, mostrar erro e impedir o envio do formulário
    if (duplicateSkills.length > 0) {
        event.preventDefault(); // Impedir o envio do formulário

        alert('Você não pode selecionar a mesma competência como Necessária e Desejável.');
    }
});

function validatePasswords() {
    const password = document.getElementById('input-password').value;
    const confirmPassword = document.getElementById('input-password-confirm').value;
    const errorMessage = document.getElementById('password-error');

    if (password !== confirmPassword) {
        errorMessage.style.display = 'block';
        return false;
    } else {
        errorMessage.style.display = 'none';
        return true;
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    form.addEventListener('submit', (event) => {
        if (!validatePasswords()) {
            event.preventDefault();
        }
    });

    document.getElementById('input-password-confirm').addEventListener('input', validatePasswords);
});