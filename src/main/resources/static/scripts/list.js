document.addEventListener('DOMContentLoaded', function () {
    const competenciesData = document.getElementById('competenciesData').value;
    const availableCompetencies = JSON.parse(competenciesData);

    const competencyInput = document.getElementById('competencies');
    const suggestionsBox = document.getElementById('competencySuggestions');

    competencyInput.addEventListener('input', function () {
        const query = this.value.toLowerCase().trim();
        suggestionsBox.innerHTML = '';

        if (query) {
            const suggestions = availableCompetencies.filter(c => c.toLowerCase().includes(query));

            if (suggestions.length > 0) {
                suggestionsBox.style.display = 'block';
                suggestions.forEach(suggestion => {
                    const suggestionItem = document.createElement('li');
                    suggestionItem.classList.add('list-group-item');
                    suggestionItem.style.cursor = 'pointer';
                    suggestionItem.textContent = suggestion;

                    suggestionItem.addEventListener('click', function () {
                        addCompetency(suggestion);
                        suggestionsBox.style.display = 'none';
                    });

                    suggestionsBox.appendChild(suggestionItem);
                });
            } else {
                suggestionsBox.style.display = 'none';
            }
        } else {
            suggestionsBox.style.display = 'none';
        }
    });

    competencyInput.addEventListener('blur', function () {
        setTimeout(function () {
            suggestionsBox.style.display = 'none';
        }, 200);
    });

    function addCompetency(competency) {
        const currentValue = competencyInput.value;
        if (!currentValue.includes(competency)) {
            competencyInput.value = currentValue ? `${currentValue}, ${competency}` : competency;
        }
    }
});