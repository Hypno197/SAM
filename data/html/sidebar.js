const template = document.createElement('template')
template.innerHTML =
`<nav class="main-menu">
        <header class="avatar">
            <img src="img/bg_home.jpg" alt="Avatar" />
        </header>
        <ul>
            <li>
                <a href="hp_consulente.html">
                    <i class="fa fa-home"></i>
                    <span class="nav-text">
                        Pagina Consulente
                    </span>
                </a>
              
            </li>
            <li class="has-subnav">
                <a href="consulente_project.html">
                    <i class="fa fa-globe"></i>
                    <span class="nav-text">
                        I miei progetti <!-- card con progetti attuali (rimanda alle task) / passati -->
                    </span>
                </a>
                
            </li>
            <li class="has-subnav">
                <a href="consulente_task.html">
                    <i class="fa fa-book"></i>
                    <span class="nav-text">
                        Le mie task <!-- stato di avanzamento -->
                    </span>
                </a>
                
            </li>
            <li class="has-subnav">
                <a href="#">
                    <i class="fa fa-comments"></i>
                   
                    <span class="nav-text">
                        Le mie note <!-- storico note inserite dalle task / progetto -->
                    </span>
                </a>
               
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-film"></i>
                    <span class="nav-text">
                        Milestones <!-- storico delle milestones inserite dal manager x progetto attivo -->
                    </span>
                </a>
            </li>
            
            <li>
               <a href="consulente_planner.html">
                   <i class="fa fa-cogs"></i>
                    <span class="nav-text">
                        Planner
                    </span>
                </a>
            </li>
            <li>
               <a href="#">
                    <i class="fa fa-map-marker"></i>
                    <span class="nav-text">
                        Classifica
                    </span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-user"></i>  
                    <span class="nav-text">
                    Il mio profilo
                    </span>
                </a>
            </li>
            
        </ul>

        <ul class="logout">
            <li>
                <a href="#">
                    <i class="fa fa-info"></i>  
                    <span class="nav-text">
                        Help
                        </span>                
                </a>
            </li>
            <li>
               <a href="#">
                     <i class="fa fa-power-off"></i>
                    <span class="nav-text">
                        Logout
                    </span>
                </a>
            </li>  
        </ul>
    </nav>`
    document.body.appendChild(template.content)