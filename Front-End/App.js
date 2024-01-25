const postContainer = document.getElementById("post-container");
const apiUrl = "http://localhost:8080/api/v1/posts/";
const addPostForm = document.getElementById("add-post-form");
const title = document.getElementById("title-value");
const content = document.getElementById("content-value");
const postList = document.querySelectorAll(".post-list");
const btn = document.getElementById("add-post-btn");

async function fetchData() {

    try {

        const response = await fetch(apiUrl);
        if (!response.ok) {

            throw new Error("something went wrong")
        }

        const data = await response.json();

        return data;

    } catch (error) {

        console.log(error);
    }

}


fetchData().then((arr) => display(arr));


function display(array) {

    postContainer.innerHTML = "";

    array.forEach(element => {
        const elem = `<div  data-id=${element.id} class="post-list row">
        <h5 class="title-Text">${element.title}</h5>
        <p>${element.content}</p>
        <div class="card-links">
            <a href="#"  id="edit-post" class="card-link edit">Edit</a>
            <a href="#" id="delete-post"  class="card-link delete">Delete</a>
        </div>

    </div>`

        postContainer.innerHTML += elem;
    });

}



addPostForm.addEventListener("submit", (e) => {

    e.preventDefault();

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "Application/json"
        },
        body: JSON.stringify(

            {

                title: title.value,
                content: content.value

            }

        )

    }).then(() => fetchData().then(arr => display(arr)))
    .catch(error => console.log(error))

})



postContainer.addEventListener('click', (e) => {
    if (e.target.tagName === 'A') {
        const isDeletePressed = e.target.id === "delete-post";
        const isUpdatePressed = e.target.id === "edit-post";

        if (isDeletePressed) {
            const id = e.target.closest('.post-list').dataset.id;

            fetch(`${apiUrl}${id}`, {
                method: "DELETE"
            }).then(() => fetchData().then(arr => display(arr)))
                .catch(error => console.log(error))

        }

        if (isUpdatePressed) {
            const id = e.target.closest('.post-list').dataset.id;
            const updatedTitle = title.value;
            const updatedContent = content.value;

            fetch(`${apiUrl}${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    title: updatedTitle,
                    content: updatedContent
                })
            }).then(() => fetchData().then(arr => display(arr)))
                .catch(error => console.log(error))
        }
    }
});





