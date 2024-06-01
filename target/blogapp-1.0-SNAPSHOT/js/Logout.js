document.addEventListener("DOMContentLoaded", function ()
{
    const logoutLink = document.getElementById("logout");

    if (logoutLink)
    {
        logoutLink.addEventListener("click", function (event)
        {
            event.preventDefault();

            fetch("/blogapp/logout", {
                method: "GET"
            })
                    .then(response => {
                        if (response.ok)
                        {
                            sessionStorage.removeItem("user");
                            window.location.href = "/blogapp/index.jsp";
                        } else
                        {
                            throw new Error("Error al cerrar sesión");
                        }
                    })
                    .catch(error => {
                        alert("Error al cerrar sesión. Por favor, inténtelo de nuevo.");
                    });
        });
    }
});