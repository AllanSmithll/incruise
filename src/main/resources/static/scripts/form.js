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