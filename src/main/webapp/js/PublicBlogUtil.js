


document.addEventListener("DOMContentLoaded", function ()
{

    class PublicBlogUtil
    {

        constructor()
        {
            if (sessionStorage.getItem('blog'))
            {
                this.blog = JSON.parse(sessionStorage.getItem('blog'));
                this.loadBlogInfo(this.blog);
                document.getElementById("addcomment").onsubmit = this.agregarComentario.bind(this);

            } else
            {
                alert('No se cargó el blog correctamente!');
            }


        }

        formatearFecha(fecha)
        {
            const fechaObj = new Date(fecha);
            const options = {year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true};
            return fechaObj.toLocaleDateString('es-ES', options);
        }

        loadBlogInfo(blog)
        {
            this.obtenerPosts();
            this.limpiarCampos();

            const titlePage = document.getElementById("page_title");
            const title = document.getElementById('blog_title');
            var titleText = document.createTextNode(blog.titulo);

            titlePage.textContent = blog.titulo;
            title.appendChild(titleText);

            if (sessionStorage.getItem("user"))
            {
                const user = JSON.parse(sessionStorage.getItem('user'));
                const autor = document.getElementById("autor");

                autor.value = user.username;
                autor.readOnly = true;
            }
        }

        obtenerPosts()
        {
            fetch('/blogapp/' + JSON.parse(sessionStorage.getItem('blog')).titulo)
                    .then(response => response.json())
                    .then(blogFetch => {
                        this.desplegarPosts(blogFetch.posts);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert(error.message);
                    });
        }

        desplegarPosts(posts)
        {
            var self = this;
            const containerGeneral = document.getElementById("blog_posts");

            if (posts.length > 0)
            {
                posts.forEach(post => {
                    this.post = post;
                    const containerPost = document.createElement("div");
                    containerPost.setAttribute("class", "post");

                    const areaPost = document.createElement("div");
                    areaPost.setAttribute("class", "post-area");

                    const postTitle = document.createElement("h3");
                    var titleText = document.createTextNode(post.titulo);
                    postTitle.appendChild(titleText);

                    const date = document.createElement("p");
                    var dateText = document.createTextNode(this.formatearFecha(post.fecha));
                    date.appendChild(dateText);
                    date.setAttribute("class", "date");

                    const postText = document.createElement("p");
                    var postCuerpo = document.createTextNode(post.cuerpo);
                    postText.appendChild(postCuerpo);

                    const imgPost = document.createElement("img");
                    imgPost.setAttribute("src", post.uriImagen);

                    const postDetails = document.createElement("div");
                    postDetails.setAttribute("class", "post-details");

                    const postEtiqueta = document.createElement("p");
                    var postEtiquetaText = document.createTextNode(post.etiquetasDTO.map(etq => "#" + etq.texto).join(", "));
                    postEtiqueta.appendChild(postEtiquetaText);

                    const buttonComentarios = document.createElement("button");
                    var buttonComentariosText = document.createTextNode(post.comentarios.length + " comentarios");
                    buttonComentarios.appendChild(buttonComentariosText);

                    buttonComentarios.addEventListener('click', function ()
                    {
                        var comentarioArea = containerPost.querySelector(".comment-area");

                        if (comentarioArea)
                        {
                            if (comentarioArea.style.display === "none")
                            {
                                comentarioArea.style.display = "block";
                            } else
                            {
                                comentarioArea.style.display = "none";
                                return;
                            }
                        } else
                        {
                            comentarioArea = document.createElement("div");
                            comentarioArea.setAttribute("class", "comment-area");

                            containerPost.appendChild(comentarioArea);
                        }


                        while (comentarioArea.firstChild)
                        {
                            comentarioArea.removeChild(comentarioArea.firstChild);
                        }

                        if (post.comentarios.length > 0)
                        {
                            const comentariosContainer = document.createElement("div");
                            comentariosContainer.setAttribute("class", "comments");

                            comentarioArea.appendChild(comentariosContainer);

                            post.comentarios.forEach(comentario => {
                                const comentarioDiv = document.createElement("div");
                                comentarioDiv.setAttribute("class", "comment");

                                const comentarioAuthor = document.createElement("p");
                                var comentarioAuthorText = document.createTextNode(comentario.autor);
                                comentarioAuthor.setAttribute("class", "autor");
                                comentarioAuthor.appendChild(comentarioAuthorText);

                                const comentarioContent = document.createElement("p");
                                var comentarioContentText = document.createTextNode(comentario.contenido);
                                comentarioContent.setAttribute("class", "comment-text");
                                comentarioContent.appendChild(comentarioContentText);

                                const comentarioDate = document.createElement("p");
                                var comentarioDateText = document.createTextNode(self.formatearFecha(comentario.fecha));
                                comentarioDate.setAttribute("class", "date");
                                comentarioDate.appendChild(comentarioDateText);

                                comentarioDiv.appendChild(comentarioAuthor);
                                comentarioDiv.appendChild(comentarioContent);
                                comentarioDiv.appendChild(comentarioDate);
                                comentariosContainer.appendChild(comentarioDiv);
                            });
                        }

                        const buttonNewComentario = document.createElement("button");
                        var buttonNewComentarioText = document.createTextNode("Nuevo Comentario");
                        buttonNewComentario.setAttribute("id", "newCommentButton");
                        buttonNewComentario.appendChild(buttonNewComentarioText);


                        buttonNewComentario.addEventListener('click', function ()
                        {
                            sessionStorage.setItem("action", "agregar");
                            self.abrirModal();
                        });

                        comentarioArea.appendChild(buttonNewComentario);
                    });



                    postDetails.appendChild(postEtiqueta);
                    postDetails.appendChild(buttonComentarios);
                    areaPost.appendChild(postTitle);
                    areaPost.appendChild(date);
                    areaPost.appendChild(postText);
                    areaPost.appendChild(imgPost);
                    areaPost.appendChild(postDetails);
                    containerPost.appendChild(areaPost);
                    containerGeneral.appendChild(containerPost);
                });
            }
        }

        limpiarCampos()
        {
            document.getElementById("modalComment").style.display = "none";
            document.getElementById("autor").value = '';
            document.getElementById("contenido").value = '';
        }

        abrirModal()
        {
            var self = this;
            var modalAddComment = document.getElementById("modalComment");

            modalAddComment.style.display = "block";


            document.getElementById("close").addEventListener('click', function ()
            {
                self.limpiarCampos();
            });

            window.onclick = (event) => {
                if (event.target == modalAddComment)
                {
                    self.limpiarCampos();
                }
            }
        }

        agregarComentario(evt)
        {
            evt.preventDefault();
            let comentario = {};

            comentario.autor = document.getElementById("autor").value;
            comentario.contenido = document.getElementById("contenido").value;
            comentario.id_post = this.post.id;

            console.log(JSON.stringify(comentario));


            fetch('/blogapp/addPostComment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(comentario)
            })
                    .then(response => {
                        if (!response.ok)
                        {
                            throw new Error('Error en la respuesta del servidor');
                        }

                        alert("Su comentario ha sido publicado con éxito.");
                        this.limpiarCampos();
                        window.location.replace("./public_blog.jsp");
                    })
                    .catch(err => {
                        console.error("Error al publicar el comentario: ", err);
                        alert("Su comentario no ha sido publicado con éxito. Intente de nuevo más tarde.");
                    });

        }

    }

    new PublicBlogUtil();
});
