/* to do list */
document.addEventListener("DOMContentLoaded", function () {
  window.addEventListener("load", function () {
    fillProjectIDSelect("newTaskProjectID");
    fillProjectIDSelect("searchProjectID");
    fillUsersFilter();
    fillStatusSelect("newTaskPriority");
    fillStatusSelect("searchStatus");
    fillStatusSelect("updateTaskPriority");
    showTasks();
  });
  window.showTasks = function() {
    document.getElementById("task-list").innerHTML = "";
    showTasks()
  }


  //funzione di servizio che prende 4 argomenti e riempe una select
  //array: lista di elementi che hanno una proprieta id e una proprieta "nome"
  //nameProp: definisce il "nome" ovvero quale sarà la casella che contiene il testo mostrato nella select
  //message: in caso il db non ritorna risultati inserisce nella select un option null con un messaggio
  function populateSelectWithArray(array, selectId, nameProp, message) {
    const selectElement = document.getElementById(selectId);
    if (array.length > 0) {
      selectElement.innerHTML = "<option>Nessuna scelta</option>";
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

  //RIEMPE PROJECTARR CON TUTTI I PROGETTI ASSOCIATI AL MANAGER
  //usando la populateSelectWithArray(projectArr)
  async function fillProjectIDSelect(id) {
    try {
      const response = await fetch(
        "http://localhost:8080/api/projects/manager",
        {
          method: "GET",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        const data = await response.json();
        projectArr = [];
        data.forEach((element) => {
          projectArr.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching projects:", error);
    }
    populateSelectWithArray(
      projectArr,
      id,
      "project_name",
      "Crea prima un progetto!"
    );
    document.getElementById("newTaskProjectID").value = 0;
  }
  //VARIABILI DI SERVIZIO
  const token = localStorage.getItem("token");
  //array che contiene progetti
  let projectArr = [];
  //lista user di un progetto specifico
  let userArr = [];
  //tutti gli user associati a tutti i progetti del manager
  let allUsers = [];
  //costanti che puntano agli elementi sulla pagina
  const taskList = document.getElementById("task-list");
  const addTaskForm = document.getElementById("addTaskForm");
  const addTaskModal = document.getElementById("addTaskModal");
  const updateTaskModal = document.getElementById("updateTaskModal");
  const changePriorityModal = document.getElementById("changePriorityModal");
  const projectSelect = document.getElementById("newTaskProjectID");

  //
  //CARICA LE TASK E MOSTRA A CLIENT
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
                <div class="badge border-1" style="background-color : ${taskStatusColor}">${taskPriority}</div>
            </div>
            <div class="ms-auto text-center col-8">
            <div class="widget-subheading">Creata il: ${task.start_date}</div>
                <div class="widget-heading">${taskName}</div>
                <div class="widget-subheading">${taskDescription}</div>
            </div>
        <div class="widget-content-right col-2">
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
  //PRENDE LE TASK ASSEGNATE AL MANAGER E RIEMPE TASKARR
  async function getManagerTasks() {
    try {
      const response = await fetch("http://localhost:8080/api/tasks/manager", {
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
  //PRENDE LA LISTA DI TUTTI I CONSULENTI DI TUTTI I PROGETTI ASSEGNATI AL MANAGER
  async function getUsers() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/projects/manager",
        {
          method: "GET",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        const data = await response.json();
        data.forEach((element) => {
          element.projectUsers.forEach((user) => allUsers.push(user));
        });
        console.log(allUsers);
      }
    } catch (error) {
      console.error("Error fetching projects:", error);
    }
  }
  //INSERIMENTO NUOVA TASK
  //  mostra add Task Modal
  window.showAddTaskModal = function () {
    addTaskModal.style.display = "block";
  };

  // chiudi add Task Modal
  window.closeAddTaskModal = function () {
    addTaskModal.style.display = "none";
  };
  //quando submitti il form convalida i dati e manda tutto a db
  //qui chiama le altre funzioni descritte sotto
  addTaskForm.addEventListener("submit", async function (event) {
    event.preventDefault();
    await saveTask();
    addTask();
    showTasks();
  });

  //aggiunge la task e resetta il form
  function addTask() {
    document.getElementById("task-list").innerHTML = "";
    addTaskForm.reset();
    closeAddTaskModal();
  }

  //RIMOZIONE USER ASSEGNATO

  async function removeUserFromTask(taskID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/unassign/${taskID}`,
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

  window.unassignUser = function (taskID) {
    removeUserFromTask(taskID);
  };

  //PER SALVARE NUOVA TASK A DB
  async function saveTask() {
    //valori form
    const taskName = document.getElementById("newTaskName").value;
    const taskDescription = document.getElementById("newTaskDescription").value;
    const taskStatusID = document.getElementById("newTaskPriority").value;
    const taskEndDate = document.getElementById("newTaskEndDate").value;
    const taskProjectID = document.getElementById("newTaskProjectID").value;
    let taskUserID = document.getElementById("newTaskUserID").value;
    //se user isNaN(se select contiene messaggio campo vuoto o altro che non sia un) setta user id NULL
    if (isNaN(taskUserID)) taskUserID = null;
    try {
      const response = await fetch("http://localhost:8080/api/tasks", {
        method: "POST",
        headers: {
          token: token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          task_name: taskName,
          task_desc: taskDescription,
          project_id: taskProjectID,
          user_id: taskUserID,
          end_date: taskEndDate,
          completion_date: null,
          status: {
            id: taskStatusID,
          },
        }),
      });
      if (response.status === 201) {
        let listaProgetto = document.getElementById(
          `listaProgetto${taskProjectID}`
        );
        if (listaProgetto == null) {
          taskList.appendChild(projectListItem);
          projectListItem.appendChild(listItem);
        } else listaProgetto.appendChild(listItem);
      }
    } catch (error) {
      console.error("Error saving task: ", error);
    }
  }

  //riempe la select degli user assegnati al progetto selezionato
  async function fillUserIDSelect(projectID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/projects/${projectID}/users`,
        {
          method: "GET",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) console.log(response);
      {
        const data = await response.json();
        userArr = [];
        data.forEach((element) => {
          userArr.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching users:", error);
    }
    populateSelectWithArray(
      userArr,
      "newTaskUserID",
      "nomeCompleto",
      "Non ci sono consulenti assegnati!"
    );
  }
  //aggiorna la select dell'user con gli user associati al progetto valore della select
  document
    .getElementById("newTaskProjectID")
    .addEventListener("change", function () {
      let projectID = projectSelect.value;
      fillUserIDSelect(projectID);
      document.getElementById("newTaskUserID").removeAttribute("disabled");
    });
  ///
  //MODIFICA TASK
  //varibili di servizio
  let taskUpdateID;
  let taskUpdateUserID;
  let taskUpdateProjectID;
  //  apri update Task Modal
  window.showUpdateTaskModal = function (taskID, projectID, userID) {
    taskUpdateID = taskID;

    taskUpdateProjectID = projectID;
    taskUpdateUserID = userID;
    updateTaskModal.style.display = "block";
  };

  // chiudi update taskModal
  window.closeUpdateTaskModal = function () {
    updateTaskModal.style.display = "none";
  };

  updateTaskForm.addEventListener("submit", async function (event) {
    event.preventDefault();
    document.getElementById("task-list").innerHTML = "";
    await updateTask(taskUpdateID, taskUpdateProjectID, taskUpdateUserID);
    addTaskForm.reset();
    showTasks();
    closeUpdateTaskModal();
  });

  async function updateTask(taskID) {
    //valori form
    const taskName = document.getElementById("updateTaskName").value;
    const taskDescription = document.getElementById(
      "updateTaskDescription"
    ).value;
    const taskStatusID = document.getElementById("updateTaskPriority").value;
    const taskEndDate = document.getElementById("updateTaskEndDate").value;
    console.log(taskID);
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/${taskID}`,
        {
          method: "PUT",
          headers: {
            token: token,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            id: taskID,
            task_name: taskName,
            task_desc: taskDescription,
            end_date: taskEndDate,
            status: {
              id: taskStatusID,
            },
          }),
        }
      );
    } catch (error) {
      console.error("Error saving task: ", error);
    }
  }

  //
  //ASSEGNA TASK a consulente
  //variabili di servizio
  let assignUserID;
  let projectUsers = [];

  //mostra modale per assegnare consulente a task
  window.showAssignUserModal = async function (taskID, projectID) {
    assignUserModal.style.display = "block";
    //conserva il parametro taskid nella variabile assignuser per salvarlo a db
    assignUserID = taskID;
    //prende tutti gli utenti assegnati al progetto
    await getProjectUsers(projectID);
    //funzione di servizio che riempe le opzioni di una select
    populateSelectWithArray(
      projectUsers,
      "assignUserSelect",
      "nomeCompleto",
      "Non ci sono consulenti assegnati!"
    );
  };
  //chiude modale per assegnare consulente a task
  window.closeAssignUserModal = async function () {
    //qui salva a db chiudendo il modal e usando il valore della select determina user target
    await assignUser(assignUserID);
    document.getElementById("task-list").innerHTML = "";
    projectUsers = []
    showTasks()
    assignUserModal.style.display = "none";
  };

  //PER SALVARE A DB IL CONSULENTE ASSEGNATO ALLA TASK
  async function assignUser(taskID) {
    let userID = document.getElementById("assignUserSelect").value;
    console.log(taskID)
    console.log(userID)
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/assign/${taskID}/${userID}`,
        {
          method: "PUT",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        console.log("pippo")
      }
    } catch (error) {
      console.error("Error fetching project users:", error);
    }
  }
  //usata per riempire projectUsers[] di tutti gli user che lavorano sul progetto
  async function getProjectUsers(projectID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/projects/${projectID}/users`,
        {
          method: "GET",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        const data = await response.json();
        data.forEach((element) => {
          projectUsers.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching project users:", error);
    }
  }
  //
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
  async function fillStatusSelect(selectID) {
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
    populateSelectWithArray(
      statusArr,
      selectID,
      "status",
      "Status non caricati correttamente"
    );
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

  //PER CANCELLARE LA TASK DA DB
  async function deleteTask(taskID) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/tasks/${taskID}`,
        {
          method: "DELETE",
          headers: {
            token: token,
          },
        }
      );
      if (response.status === 200) {
        return true;
      }
    } catch (error) {
      console.error("Error fetching projects:", error);
    }
  }
  //elimina la task dalla lista
  window.deleteTask = function (taskID) {
    let task = document.getElementById(`task${taskID}`);
    if (deleteTask(taskID)) task.parentElement.removeChild(task);
  };

  async function fillUsersFilter() {
    let allTheUsers = [];
    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "GET",
        headers: {
          token: token,
        },
      });
      if (response.status === 202) {
        const data = await response.json();
        data.forEach((user) => {
          allTheUsers.push(user);
        });
        populateSelectWithArray(
          allTheUsers,
          "searchUserID",
          "nomeCompleto",
          "Nessun utente trovato"
        );
      } else console.log("bambo");
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  }

  window.filterTasks = function () {
    document.getElementById("task-list").innerHTML = "";
    showTasks(true)
  };

  async function getFilteredTasks() {
    const searchName = document.getElementById("searchName").value;
    let projectID = document.getElementById("searchProjectID").value;
    if (isNaN(projectID)) projectID = null;    
    let userID = document.getElementById("searchUserID").value;
    if (isNaN(userID)) userID = null;
    let statusID = document.getElementById("searchStatus").value;
    if (isNaN(statusID)) statusID = null;
    const endDate = document.getElementById("searchEndDate").value;
    console.log(endDate)
    try {
      const response = await fetch(`http://localhost:8080/api/tasks/filter`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          token: token,
        },
        body: JSON.stringify({searchName,projectID, userID, statusID, endDate})
      });
      if (response.status === 200) {
        const data = await response.json();
        taskArr = [];
        data.forEach((element) => {
          taskArr.push(element);
        });
      }
    } catch (error) {
      console.error("Error fetching filtered tasks:", error);
    }
  }

  //js per select dinamiche e riempimento
});