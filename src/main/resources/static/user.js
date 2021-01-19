$(document).ready(function () {
    $.ajax({
        url: "/user"
    }).then(function (data) {
        const parent = document.getElementById("usr");
        data.forEach((user) => {
            const newItem = createListItem(user);
            parent.appendChild(newItem);
        });
    });
});

function createListItem(user) {
    const item = document.createElement("tr");
    const roles = user.roles.reduce((accum, role) => {
        return accum === "" ?
            accum + role.name :
            accum + ", " + role.name;
    }, "");
    const content = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${roles}</td>  
        `;
    item.innerHTML = content;
    return item;
}