document.addEventListener('DOMContentLoaded', function() {
    // Função para abrir e fechar a navegação
    const navbarToggle = document.getElementById('navbar-toggle');
    const navUl = document.querySelector('nav ul');
  
    navbarToggle.addEventListener('click', function() {
      if (navUl.style.display === '' || navUl.style.display === 'none') {
        navUl.style.display = 'block';
      } else {
        navUl.style.display = 'none';
      }
  
      // Alterna o estado do hamburger menu
      this.classList.toggle('active');
    });
  
    // Se um link tem um dropdown, adiciona toggle para o submenu
    const navLinks = document.querySelectorAll('nav ul li a:not(:only-child)');
    navLinks.forEach(function(link) {
      link.addEventListener('click', function(e) {
        const navbarDropdown = this.nextElementSibling;
        
        if (navbarDropdown) {
          navbarDropdown.style.display = navbarDropdown.style.display === 'block' ? 'none' : 'block';
        }
  
        // Fecha dropdowns ao selecionar outro
        const allDropdowns = document.querySelectorAll('.navbar-dropdown');
        allDropdowns.forEach(function(dropdown) {
          if (dropdown !== navbarDropdown) {
            dropdown.style.display = 'none';
          }
        });
  
        e.stopPropagation();
      });
    });
  
    // Clica fora do dropdown para fechar o dropdown
    document.addEventListener('click', function() {
      const allDropdowns = document.querySelectorAll('.navbar-dropdown');
      allDropdowns.forEach(function(dropdown) {
        dropdown.style.display = 'none';
      });
    });
  });
  