document.addEventListener("DOMContentLoaded", function ()
{

    class BlogUtil
    {

        constructor()
        {
            if (sessionStorage.getItem('user'))
            {
                if (sessionStorage.getItem('blog'))
                {
                    this.blog = JSON.parse(sessionStorage.getItem('blog'));
                    this.loadBlogInfo(this.blog);
                    document.getElementById("addpost").onsubmit = this.agregarPost.bind(this);
                    this.abrirModal();
                } else
                {
                    alert('No se cargó el blog correctamente!');
                }
            } else
            {
                window.location.replace("./index.jsp");
            }
        }

        loadBlogInfo(blog)
        {
            this.limpiarCampos();
            this.obtenerPosts();

            const titlePage = document.getElementById("page_title");
            const title = document.getElementById('blog_title');
            const date = document.getElementById('blog_date');
            const image = document.getElementById('blog_image');

            var titleText = document.createTextNode(blog.titulo);
            var dateText = document.createTextNode(this.formatearFecha(blog.fecha));

            titlePage.textContent = blog.titulo;
            title.appendChild(titleText);
            date.appendChild(dateText);
            image.setAttribute('src', blog.imagen);
        }

        formatearFecha(fecha)
        {
            const fechaObj = new Date(fecha);
            const options = {year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true};
            return fechaObj.toLocaleDateString('es-ES', options);
        }

        agregarPost(evt)
        {
            evt.preventDefault();
            let post = {};

            post.titulo = document.getElementById("titulo").value;
            post.cuerpo = document.getElementById("cuerpo").value;
            post.uriImagen = document.getElementById("imagenuri").value;
            post.id_blog = this.blog.id;
            post.etiquetas = document.getElementById("etiquetas").value.split(',').map(etiqueta => etiqueta.trim());

            if (sessionStorage.getItem("action") === "agregar")
            {
                fetch('/blogapp/addBlogPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(post)
                })
                        .then(response => {
                            if (!response.ok)
                            {
                                throw new Error('Error en la respuesta del servidor');
                            }

                            alert("Su publicación ha sido publicada con éxito.");
                            window.location.replace("./blog_index.jsp");
                        })
                        .catch(err => {
                            console.log(err);
                            alert("Su publicación no ha sido publicada con éxito. Intente de nuevo mas tarde.");
                        });
            } else if (sessionStorage.getItem("action") === "editar")
            {

                post.id = JSON.parse(sessionStorage.getItem('post')).id;
                post.id_etiquetas = JSON.parse(sessionStorage.getItem('post')).etiquetasDTO.map(etiqueta => etiqueta.id);

                console.log(post);

                fetch('/blogapp/updateBlogPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(post)
                })
                        .then(response => {
                            if (!response.ok)
                            {
                                throw new Error('Error en la respuesta del servidor');
                            }

                            alert("Su publicación ha sido actualizada con éxito.");
                            window.location.replace("./blog_index.jsp");
                        })
                        .catch(err => {
                            console.log(err);
                            alert("Su publicación no ha sido actualizada con éxito. Intente de nuevo mas tarde.");
                        });
            }

        }

        abrirModal()
        {
            var self = this;
            var modalAddPost = document.getElementById("modalPost");

            document.getElementById("newPostButton").addEventListener('click', function ()
            {
                document.getElementById("modal-action").appendChild(document.createTextNode("Agregar un post"));
                sessionStorage.setItem("action", "agregar");
                document.getElementById("btnFormPost").appendChild(document.createTextNode("Agregar post"));
                modalAddPost.style.display = "block";
            });

            document.getElementById("close").addEventListener('click', function ()
            {
                self.limpiarCampos();
            });

            window.onclick = (event) => {
                if (event.target == modalAddPost)
                {
                    self.limpiarCampos();
                }
            }
        }

        limpiarCampos()
        {
            document.getElementById("btnFormPost").textContent = "";
            document.getElementById("modal-action").textContent = "";
            document.getElementById("modalPost").style.display = "none";
            document.getElementById("titulo").value = '';
            document.getElementById("cuerpo").value = '';
            document.getElementById("imagenuri").value = '';
            document.getElementById("etiquetas").value = '';
        }

        obtenerPosts()
        {
            fetch('/blogapp/myposts?username=' + JSON.parse(sessionStorage.getItem('user')).username)
                    .then(response => response.json())
                    .then(posts => {
                        this.desplegarPosts(posts);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert(error.message);
                    });
        }

        desplegarPosts(posts)
        {
            const containerGeneral = document.getElementById("mis_posts");
            var self = this;
            if (posts.length > 0)
            {
                posts.forEach(post => {
                    if (post.blogDTO.id === this.blog.id)
                    {

                        const containerPost = document.createElement("div");
                        containerPost.setAttribute("class", "post-card");

                        const title = document.createElement("h3");
                        var titleText = document.createTextNode(post.titulo);
                        title.appendChild(titleText);
                        containerPost.appendChild(title);

                        const date = document.createElement("p");
                        var dateText = document.createTextNode(this.formatearFecha(post.fecha));
                        date.appendChild(dateText);
                        date.setAttribute("class", "date");
                        containerPost.appendChild(date);

                        const postButtons = document.createElement("div");
                        postButtons.setAttribute("class", "post-buttons");

                        const buttonEdit = document.createElement("button");
                        var buttonEditText = document.createTextNode("Editar");
                        buttonEdit.appendChild(buttonEditText);

                        const buttonDelete = document.createElement("button");
                        var buttonDeleteText = document.createTextNode("Eliminar");
                        buttonDelete.appendChild(buttonDeleteText);

                        buttonEdit.addEventListener('click', function ()
                        {
                            sessionStorage.setItem("action", "editar");
                            sessionStorage.setItem("post", JSON.stringify(post));
                            document.getElementById("modal-action").appendChild(document.createTextNode("Editar un post"));
                            document.getElementById("modalPost").style.display = "block";
                            document.getElementById("titulo").value = post.titulo;
                            document.getElementById("cuerpo").value = post.cuerpo;
                            document.getElementById("imagenuri").value = post.uriImagen;
                            let etiquetas = post.etiquetasDTO.map(etq => etq.texto).join(", ");
                            document.getElementById("etiquetas").value = etiquetas;

                            document.getElementById("btnFormPost").appendChild(document.createTextNode("Editar post"));

                        });

                        buttonDelete.addEventListener('click', function ()
                        {
                            sessionStorage.setItem("action", "eliminar");
                            sessionStorage.setItem("post", JSON.stringify(post));
                            self.eliminarPost(post);
                        });


                        postButtons.appendChild(buttonEdit);
                        postButtons.appendChild(buttonDelete);

                        containerPost.appendChild(postButtons);
                        containerGeneral.appendChild(containerPost);
                    }
                });
            }
        }

        eliminarPost(postEliminar)
        {
            let post = {};

            if (sessionStorage.getItem("action") === "eliminar")
            {
                var modalEliminar = document.createElement("div");
                modalEliminar.setAttribute("class", "modal");
                modalEliminar.style.display = "block";

                var modalContent = document.createElement("div");
                modalContent.setAttribute("class", "modal-content");

                var formEliminar = document.createElement("form");

                var mensajeEliminar = document.createElement("p");
                mensajeEliminar.appendChild(document.createTextNode("Desea eliminar el post '" + postEliminar.titulo + "'?"));
                mensajeEliminar.appendChild(document.createElement('br'));
                mensajeEliminar.appendChild(document.createTextNode("Este post tiene " + postEliminar.comentarios.length + " comentarios."));
                mensajeEliminar.style.textAlign = "center";

                var botonEliminar = document.createElement("button");
                botonEliminar.appendChild(document.createTextNode("Eliminar post"));

                var botonCancelar = document.createElement("button");
                botonCancelar.appendChild(document.createTextNode("Cancelar"));

                formEliminar.appendChild(mensajeEliminar);
                formEliminar.appendChild(botonEliminar);
                formEliminar.appendChild(botonCancelar);

                modalContent.appendChild(formEliminar);
                modalEliminar.appendChild(modalContent);

                document.getElementById("eliminar_post").appendChild(modalEliminar);

                botonCancelar.addEventListener('click', function ()
                {
                    modalEliminar.style.display = "none";
                });

                botonEliminar.addEventListener('click', function ()
                {
                    post.id = postEliminar.id;
                    post.id_comentarios = postEliminar.comentarios.map(comentario => comentario.id);
                    post.id_etiquetas = postEliminar.etiquetasDTO.map(etiqueta => etiqueta.id);

                    fetch('/blogapp/deleteBlogPost', {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(post)
                    })
                            .then(response => {
                                if (!response.ok)
                                {
                                    throw new Error('Error en la respuesta del servidor');
                                }

                                alert("Su publicación ha sido eliminada con éxito.");
                                window.location.replace("./blog_index.jsp");
                            })
                            .catch(err => {
                                console.log(err);
                                alert("Su publicación no ha sido eliminada con éxito. Intente de nuevo mas tarde.");
                            });
                });


            }
        }

    }



    new BlogUtil();
});
