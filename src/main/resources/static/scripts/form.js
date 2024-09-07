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