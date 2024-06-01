
class LoginUser
{
    constructor()
    {
        this.formLogin = document.getElementById("form-login");

        this.formLogin.onsubmit = this.loginUsuario.bind(this);
    }

    loginUsuario(evt)
    {
        evt.preventDefault();

        let correo = document.getElementById("email").value;
        let contraseña = document.getElementById("password").value;

        fetch('/blogapp/login?email=' + encodeURIComponent(correo) + '&password=' + encodeURIComponent(contraseña))
                .then(response => response.json())
                .then(blogger => {
                    if (Object.keys(blogger).length === 0)
                    {
                        throw new Error("Error al ingresar.");
                    } else
                    {
                        sessionStorage.setItem('user', JSON.stringify(blogger));
                        window.location.replace("./my_blogs.jsp");
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert(error.message);
                });
    }
}

window.onload = () => new LoginUser();
