let projects=[];

let table = document.getElementById("projectTable");
let token = localStorage.getItem('token')
document.addEventListener("DOMContentLoaded", () => {
    try {
        const response = fetch ('http://local.sam.com:8080/api/projects/manager', {
                method : 'GET',
                headers : {
                    'token' : `${token}`
                }
            }) 
           
        }    catch (error)
            {  
                console.error('Error:', error);
            }
 });
/* .then (res => res.json) 
        .then (json => {
            json.map(project=> {
                projects.push(project)
            })
        }) .catch (e => {
            console.log(e);
        })
        console.log(projects)*/