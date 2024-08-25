document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener("load", function () {
    });

    async function showMilestones(projectID){
        

    }
});

 
 
 
 /*//CARICA LE TASK E MOSTRA A CLIENT
  //qui dentro si trova il template delle task mostrate al manager
  //e del nome della categoria.
  //showtasks aggiorna a db quello che viene mostrato a schermo e viene
  //chiamata piu volte.
  async function showTasks(filter) {
    if (filter == undefined) await getManagerTasks();
    else if (filter == true){
        await getFilteredTasks();
    } 
    allUsers = []
    await getUsers();
    taskArr.forEach((task) => {
        const taskID = task.id;
        const taskName = task.task_name;
        const taskDescription = task.task_desc;
        const taskPriority = task.status.status;
        const taskStatusColor = task.status.color;
        let taskMilestoneName;
        if(task.milestone != null){
          taskMilestoneName = task.milestone.mile_name;
        }
        const taskValue = task.value;
        let taskEndDate = task.end_date;
        if (taskEndDate == null) taskEndDate = "Scadenza indefinita";
        const taskProjectID = task.project_id;
      const taskUserID = task.userID;
      const taskProjectName = document.getElementById(
        `project_name${taskProjectID}`
      ).textContent;
      let taskUserName;
      allUsers.forEach((user) => {
        if (user.id == taskUserID) taskUserName = user.nomeCompleto;
      });
      if (taskUserName == null) {
        taskUserName = "Non assegnata";
      }
      let projectListItem = document.createElement("ul");
      projectListItem.className = "p-2";
      projectListItem.id = `listaProgetto${taskProjectID}`;
      //TEMPLATE DEL NOME DELLA CATEGORIA
      projectListItem.innerHTML = `
          <h5 class="mb-3 text-center">${taskProjectName}</h5>`;
      //
      let listItem = document.createElement("li");
      listItem.className = "list-group-item";
      listItem.id = `task${taskID}`;
      //TEMPLATE DELLA TASK
      listItem.innerHTML = `
<div class="row"> 
    <div class="widget-content p-0">
        <div class="widget-content-wrapper">
            <div class="widget-content-left col-2">
                <div class="widget-heading">${taskUserName}</div>
                <div class="widget-subheading">Scadenza: ${taskEndDate}</div>
                <div class="badge border-1" style="background-color : ${taskStatusColor}">${taskPriority} <br> <br> ${taskValue} <i class="fa-solid fa-dragon" style="color:lime" ></i> </div>
            </div>
            <div class="ms-auto text-center col-8">
            <span class="badge text-bg-dark">${taskMilestoneName}</span>
            <div class="widget-heading">${taskName}</div>
            <div class="widget-subheading">${taskDescription}</div>
            </div>
            <div class="widget-content-right col-2">
            <div class="widget-subheading">Creata il: ${task.start_date}</div>
            <div class="dropdown-center" id="dropdown">
  
                <button class="btn btn-outline-secondary border-0"  type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fa-solid fa-ellipsis"></i>
                </button>
                <button class="ms-2 border-0 btn-transition btn btn-outline-secondary" onclick="showUpdateTaskModal(${taskID},${taskProjectID}, ${taskUserID})">
                    <i class="fa-regular fa-pen-to-square"></i>   
                </button>
                <button class="border-0 btn-transition btn btn-outline-danger" onclick="deleteTask(${taskID})">
                    <i class="fa-solid fa-trash-can"></i>
                </button>
                <ul class="dropdown-menu" style="background-color: #2a2727">
                <li>
              
                <button class="ms-1 border-0 btn-transition btn btn-outline-success" onclick=" showChangePriorityModal(${taskID})">
                    <i class="fa-solid fa-clock-rotate-left"></i>               
                </button>
                <button class="ms-1 border-0 btn-transition btn btn-outline-warning" onclick="showAssignUserModal(${taskID},${taskProjectID})">
                    <i class="fa-solid fa-user-pen"></i>
                </button>
         
                <button class="ms-1 border-0 btn-transition btn btn-outline-danger" onclick="unassignUser(${taskID})">
                    <i class="fa-solid fa-user-minus"></i>    
                </button>
         
                </li>
              </ul>
            
            </div>
        </div>
    </div>
</div>
          `;
      //
      let listaProgetto = document.getElementById(
        `listaProgetto${taskProjectID}`
      );
      if (listaProgetto == null) {
        taskList.appendChild(projectListItem);
        projectListItem.appendChild(listItem);
      } else listaProgetto.appendChild(listItem);
    });
  }
    */