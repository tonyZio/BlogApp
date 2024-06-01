

document.addEventListener("DOMContentLoaded", function ()
{



    class AddBlogPost
    {

        constructor()
        {
            if (sessionStorage.getItem("user"))
            {
                document.getElementById("addpost").onsubmit = this.agregarPost;
            } else
            {
                window.location.replace("./index.jsp");
            }


        }

        agregarPost(evt)
        {

            evt.preventDefault(); //para que no actue el boton al hacer load la pagina

            if (sessionStorage.getItem('user'))
            {

                let post = new Object();

                post.titulo = document.getElementById("titulo").value;
                post.cuerpo = document.getElementById("cuerpo").value;
                post.uriImagen = document.getElementById("imagenuri").value;
                post.etiquetas = document.getElementById("etiquetas").value.split(',').map(etiqueta => etiqueta.trim());

                console.log(post);

            }

        } 

    }


    new AddBlogPost();


});