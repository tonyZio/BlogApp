class GetBlogPosts {
    constructor() {
        this.fetchPosts();
    }

    fetchPosts() {
        fetch('/blogapp/blogPosts') // URL del servlet que obtiene los posts del blog
            .then(response => response.json())
            .then(posts => {
                this.renderPosts(posts);
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    }

    renderPosts(posts) {
        const postsContainer = document.getElementById('postsContainer');

        if (posts.length > 0) {
            posts.forEach(post => {
                const postCard = document.createElement('div');
                postCard.className = 'post-card';

                const postTitle = document.createElement('h2');
                postTitle.className = 'post-title';
                postTitle.innerText = post.titulo;
                postCard.appendChild(postTitle);

                const postMeta = document.createElement('p');
                postMeta.className = 'post-meta';
                postMeta.innerText = `Creado el: ${post.fecha}`;
                postCard.appendChild(postMeta);

                const postContent = document.createElement('p');
                postContent.className = 'post-content';
                postContent.innerText = post.descripcion;
                postCard.appendChild(postContent);

                const postTags = document.createElement('p');
                postTags.className = 'post-tags';
                postTags.innerText = `Etiquetas: ${post.etiquetas.join(', ')}`;
                postCard.appendChild(postTags);

                const postActions = document.createElement('div');
                postActions.className = 'post-actions';

                const editButton = document.createElement('button');
                editButton.className = 'edit-button';
                editButton.innerText = 'Editar';
                postActions.appendChild(editButton);

                const deleteButton = document.createElement('button');
                deleteButton.className = 'delete-button';
                deleteButton.innerText = 'Eliminar';
                postActions.appendChild(deleteButton);

                const commentButton = document.createElement('button');
                commentButton.className = 'comment-button';
                commentButton.innerText = `${post.comentarios.length} Comentarios`;
                commentButton.addEventListener('click', () => {
                    this.toggleComments(post.id);
                });
                postActions.appendChild(commentButton);

                postCard.appendChild(postActions);

                const commentsSection = document.createElement('div');
                commentsSection.className = 'comments-section';
                commentsSection.id = `comments-${post.id}`;

                post.comentarios.forEach(comment => {
                    const commentDiv = document.createElement('div');
                    commentDiv.className = 'comment';

                    const commentAuthor = document.createElement('span');
                    commentAuthor.className = 'comment-author';
                    commentAuthor.innerText = comment.autor;
                    commentDiv.appendChild(commentAuthor);

                    const commentDate = document.createElement('span');
                    commentDate.className = 'comment-date';
                    commentDate.innerText = comment.fecha;
                    commentDiv.appendChild(commentDate);

                    const commentContent = document.createElement('p');
                    commentContent.innerText = comment.contenido;
                    commentDiv.appendChild(commentContent);

                    commentsSection.appendChild(commentDiv);
                });

                postCard.appendChild(commentsSection);

                const addCommentButton = document.createElement('button');
                addCommentButton.className = 'add-comment-button';
                addCommentButton.innerText = 'Añadir comentario';
                addCommentButton.addEventListener('click', () => {
                    this.addComment(post.id);
                });
                postCard.appendChild(addCommentButton);

                postsContainer.appendChild(postCard);
            });
        } else {
            const noPostsMessage = document.createElement('p');
            noPostsMessage.innerText = 'No hay posts disponibles.';
            postsContainer.appendChild(noPostsMessage);
        }
    }

    toggleComments(postId) {
        const commentsSection = document.getElementById(`comments-${postId}`);
        commentsSection.style.display = commentsSection.style.display === 'none' ? 'block' : 'none';
    }

    addComment(postId) {
        // Lógica para añadir un nuevo comentario
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new GetBlogPosts();
});
