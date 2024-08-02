/* to do list */
document.addEventListener("DOMContentLoaded", function () {
  window.addEventListener("load", function () {
    fillStatusArr();
    showTasks();
  });
  //VARIABILI DI SERVIZIO
  const token = localStorage.getItem("token");

  //array che contiene progetti
  let projectArr = [];
  //costanti che puntano agli elementi sulla pagina
  const taskList = document.getElementById("task-list");
  const complTaskList = document.getElementById("completed-task-list");
  const changePriorityModal = document.getElementById("changePriorityModal");

  //
  //CARICA LE TASK E MOSTRA A CLIENT
  //qui dentro si trova il template delle task mostrate al consulente
  //e del nome della categoria.
  //showtasks aggiorna a db quello che viene mostrato a schermo e viene
  //chiamata piu volte.
  async function showTasks() {
    await getUserTasks();
    await fillProjectArr();
    console.log(projectArr);
    taskArr.forEach((task) => {
      const taskID = task.id;
      const taskName = task.task_name;
      const taskDescription = task.task_desc;
      //status della task
      const taskPriority = task.status.status;
      const taskStatusColor = task.status.color;
      //scadenza
      let taskEndDate = task.end_date;
      const taskCreated = task.start_date;
      //se c'è la scadenza la mette altrimenti scrive indefinita
      if (taskEndDate == null) taskEndDate = "Scadenza indefinita";
      const taskProjectID = task.project_id;
      let taskProjectName;
      projectArr.forEach((project) => {
        if (project.id == taskProjectID) taskProjectName = project.project_name;
      });
      //
      let projectListItem = document.createElement("li");
      projectListItem.className = "p-2";
      projectListItem.id = `listaProgetto${taskProjectID}`;
      //TEMPLATE DEL NOME DELLA CATEGORIA
      projectListItem.innerHTML = `
            <h5 class="text-center">${taskProjectName}</h5>`;
      //
      let listItem = document.createElement("li");
      listItem.className = "list-group-item";
      listItem.id = `task${taskID}`;
      //TEMPLATE DELLA TASK
      listItem.innerHTML = `
        <div class="row"> 
                <div class="widget-content p-0">
                <div class="widget-content-wrapper">
                <div class="col-2"> 
                <div class="widget-content-left">
                <div class="widget-heading border-1" style="color : ${taskStatusColor}">${taskPriority}</div>
                <div class="widget-subheading">Creazione: <br>${taskCreated}</div>                
                <div class="widget-subheading ms-auto">Scadenza: <br>${taskEndDate}</div>   
                </div>
                </div>
                <div class="col-8"> 
                <div class="p-2 text-center border-start border-end">
                <div class="widget-heading mb-2"><strong>${taskName}</strong></div>
                <div class="widget-subheading">${taskDescription}</div>
                </div>
                </div>
                <div class="col-1"> 
                <div class="widget-content-right">
                <button class="ms-1 border-0 btn-transition btn btn-outline-warning" onclick="showChangePriorityModal(${taskID})">
                <i class="fa-solid fa-clock-rotate-left"></i>
                </button>
             
                <button class="ms-1 border-0 btn-transition btn btn-outline-success" onclick="completeTask(${taskID})">
                     <i class="fa-solid fa-check"></i> 
               </button>
                </div>
                </div>
                </div>
                </div>
                </div>
            `;
      //
      let listaProgetto;
      if (taskPriority == "completata") {
        listaProgetto = document.getElementById(
          `listaProgettoCompletato${taskProjectID}`
        );
      } else {
        listaProgetto = document.getElementById(
          `listaProgetto${taskProjectID}`
        );
      }

      if (listaProgetto == null && taskPriority == "completata") {
        complTaskList.appendChild(projectListItem);
        projectListItem.appendChild(listItem);
      } else if (listaProgetto == null) {
        taskList.appendChild(projectListItem);
        projectListItem.appendChild(listItem);
      } else listaProgetto.appendChild(listItem);
    });
  }
  //PRENDE LE TASK ASSEGNATE AL MANAGER E RIEMPE TASKARR
  async function getUserTasks() {
    try {
      const response = await fetch("http://localhost:8080/api/tasks/user", {
        method: "GET",
        headers: {
          token: token,
        },
      });
      if (response.status === 200) {
        const data = await response.json();
        taskArr = [];
        data.forEach((element) => {
          taskArr.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching tasks:", error);
    }
  }

  //riempe ProjectArr
  async function fillProjectArr() {
    try {
      const response = await fetch("http://localhost:8080/api/projects/user", {
        method: "GET",
        headers: {
          token: token,
        },
      });
      if (response.status === 200) {
        const data = await response.json();
        data.forEach((element) => {
          projectArr.push(element);
        });
        console.log(projectArr);
      }
    } catch (error) {
      console.error("Error fetching projects:", error);
    }
  }

  //PER METTERE TASK COMPLETATA
  //ATTUALMENTE INUTILIZZATA LATO MANAGER
  window.completeTask = function (taskID) {
    completeTask(taskID);
  };
  //FETCH
  async function completeTask(taskID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/complete/${taskID}`,
        {
          method: "GET",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        document.getElementById("task-list").innerHTML = "";
        showTasks();
      }
    } catch (error) {
      console.error("Error completing task:", error);
    }
  }
  //
  //

  //CAMBIA STATUS DELLA TASK
  //variabili di servizio
  let changePriorityID;
  let statusArr = [];

  //
  //mostra modal cambia status
  window.showChangePriorityModal = function (taskID) {
    populateSelectWithArray(
      statusArr,
      "changePrioritySelect",
      "status",
      "Status non validi"
    );
    changePriorityModal.style.display = "block";
    changePriorityID = taskID;
  };

  // chiudi modal cambia status
  window.closeChangePriorityModal = async function () {
    const statusID = document.getElementById("changePrioritySelect").value;
    await changePriority(changePriorityID, statusID);
    changePriorityModal.style.display = "none";
    document.getElementById("task-list").innerHTML = "";
    showTasks();
  };
  //riempe statusArr[] con tutti gli status delle task possibili e poi riempe
  //la SELECT nel pararmetro
  async function fillStatusArr() {
    try {
      const response = await fetch("http://localhost:8080/api/status", {
        method: "GET",
        headers: {
          token: token,
        },
      });
      if (response.status === 200) {
        const data = await response.json();
        statusArr = [];
        data.forEach((element) => {
          statusArr.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching projects:", error);
    }
  }

  //fetch e salva a db
  async function changePriority(taskID, statusID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/changestatus/${taskID}/${statusID}`,
        {
          method: "PUT",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
      }
    } catch (error) {
      console.error("Error completing task:", error);
    }
  }
  //funzione di servizio che prende 4 argomenti e riempe una select
  //array: lista di elementi che hanno una proprieta id e una proprieta "nome"
  //nameProp: definisce il "nome" ovvero quale sarà la casella che contiene il testo mostrato nella select
  //message: in caso il db non ritorna risultati inserisce nella select un option null con un messaggio
  function populateSelectWithArray(array, selectId, nameProp, message) {
    const selectElement = document.getElementById(selectId);
    if (array.length > 0) {
      selectElement.innerHTML = '<option>Nessuna scelta</option>';
      array.forEach((item) => {
        const option = document.createElement("option");
        option.value = item.id;
        option.id = `${nameProp}${item.id}`;
        option.textContent = item[nameProp];
        if (nameProp === "status") option.classList.add();
        selectElement.appendChild(option);
      });
    } else {
      selectElement.innerHTML = "";
      const option = document.createElement("option");
      option.textContent = message;
      selectElement.appendChild(option);
    }
  }
});