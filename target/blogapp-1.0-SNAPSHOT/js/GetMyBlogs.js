
class GetMyBlogs
{
    constructor()
    {
        if (sessionStorage.getItem('user'))
        {
            console.log(JSON.parse(sessionStorage.getItem('user')).username);

            fetch('/blogapp/myblogs?username=' + JSON.parse(sessionStorage.getItem('user')).username)
                    .then(response => response.json())
                    .then(blogs => {
                        this.desplegarBlogs(blogs);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert(error.message);
                    });
        } else
        {
            window.location.replace("./index.jsp");
        }
    }

    formatearFecha(fecha)
    {
        const fechaObj = new Date(fecha);
        const options = {year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true};
        return fechaObj.toLocaleDateString('es-ES', options);
    }

    desplegarBlogs(blogs)
    {
        const containerGeneral = document.getElementById("mis_blogs");

        if (blogs.length > 0)
        {
            blogs.forEach(blog => {

                const containerBlog = document.createElement("div");
                containerBlog.setAttribute("class", "blog-card");

                const contentBlog = document.createElement("div");
                contentBlog.setAttribute("class", "blog-content");

                if (blog.imagen)
                {
                    const image = document.createElement("img");
                    image.setAttribute("src", blog.imagen);
                    containerBlog.appendChild(image);
                }

                const title = document.createElement("h3");
                var titleText = document.createTextNode(blog.titulo);
                title.appendChild(titleText);
                contentBlog.appendChild(title);

                const date = document.createElement("p");
                var dateText = document.createTextNode(this.formatearFecha(blog.fecha));
                date.appendChild(dateText);
                date.setAttribute("class", "date");
                contentBlog.appendChild(date);

                const buttonVer = document.createElement("button");
                const href = document.createElement("a");
                var hrefText = document.createTextNode("Ver");
                href.appendChild(hrefText);
                buttonVer.appendChild(href);

                buttonVer.addEventListener('click', function ()
                {
                    sessionStorage.setItem("blog", JSON.stringify(blog));
                    window.location.href = 'blog_index.jsp';
                });

                contentBlog.appendChild(buttonVer);


                containerBlog.appendChild(contentBlog);
                containerGeneral.appendChild(containerBlog);
            });

        } else
        {
            containerGeneral.innerHTML = "<p>No hay blogs para mostrar!</p>";
        }
    }
}



new GetMyBlogs();
