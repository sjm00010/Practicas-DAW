let el = selector => document.querySelector(selector);
class LibrosCtrl {
    constructor() {
        this.srvUrl = "api/libros"; //REST service url
        //view-model
        this.libros = [];
    }
    init() {
        this.cargaLibros();
    }

    cargaLibros() {
        return fetch(this.srvUrl)
                .then(response => response.json())
                .then(libros => {
                    this.libros = libros;
                    this.visualizaLibros();
                    return true;
                })
                .catch(() => { //Network error
                    el('#errores').innerHTML = "Error en conexión";
                    console.error("Error en conexión");
                    return false;
                });
    }

    visualizaLibros() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.libros.forEach(libro => {
            let li = document.createElement('li');
            li.innerHTML = `<b>ISBN :</b> ${libro.ISBN} -- <b>Título : </b>${libro.titulo} 
                <a href="" onclick="ctrl.borra(${libro.ISBN},event)">Borrar</a>
                <button onclick="ctrl.detalleLibro(${libro.ISBN})">Detalles</button>`;
            ul.appendChild(li);
        });
    }
    
    detalleLibro(isbn) {
        let panel = el('#paneldetalle');
        let enviado = false;
        return fetch(this.srvUrl + "/" + isbn)
          .then(response => {
            if (response.ok) {
                enviado = true; //libro accepted in server
            } //else bean-validation errors!
            return response.json();
        }).then(response => {
            let error = [];
            if (enviado === true) {
                console.log(`Confirmado detalle de libro: ${response.ISBN}`);
                el('#detalleIsbn').innerHTML = `${response.ISBN}`;
                el('#detalleTitulo').innerHTML = `${response.titulo}`;
                el('#detalleFecha').innerHTML = `${response.fecha}`;
                el('#detallePrecio').innerHTML = `${response.precio} €`;
                panel.style.display="block";
            } else { //show bean-validation errors
                console.warn(response);
                for (var i = 0; i < response.length; i++) {
                    error.push(response[i].message);
                }
            }
            el('#errores').innerHTML = error;
            return enviado;
        }).catch(ex => { //Network error
                el('#errores').innerHTML = "Error en conexión";
                console.error("Error en conexión");
                return enviado;
        });
    }

    borra(isbn, event) {
        let enviado = false;
        return fetch(this.srvUrl + "/" + isbn, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                enviado = true; //libro accepted in server
            } //else bean-validation errors!
            return response.json();
        }).then(response => {
            let error = [];
            if (enviado === true) {
                console.log('Confirmado borrado de libro: ' + isbn);
            } else { //show bean-validation errors
                console.warn(response);
                for (var i = 0; i < response.length; i++) {
                    error.push(response[i].message);
                }
            }
            el('#errores').innerHTML = error;
            return enviado;
        }).catch(ex => { //Network error
                el('#errores').innerHTML = "Error en conexión";
                console.error("Error en conexión");
                return enviado;
        });
    }

    alta(event) { //onsubmit handler
        event.preventDefault();
        let isbn = el('#fAlta\\:isbn').value;
        let titulo = el('#fAlta\\:titulo').value;
        console.log('alta de libro %s: %s', isbn, titulo);
        let libro = {
            ISBN: isbn,
            titulo: titulo
        };
        this.enviaLibro(libro)
                .then(enviado => {
                    if (enviado) {
                        el('#fAlta').reset();
                        this.cargaLibros();
                    }
                });
    }

    enviaLibro(libro) { //ajax request
        let enviado = false;
        return fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(libro),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                enviado = true; //libro accepted in server
            } //else bean-validation errors!
            return response.json();
        }).then(response => {
            let error = [];
            if (enviado === true) {
                console.log(`Confirmada alta de libro: ${response.ISBN}`);
            } else { //show bean-validation errors
                console.warn(response);
                for (var i = 0; i < response.length; i++) {
                    error.push(response[i].message);
                }
            }
            el('#errores').innerHTML = '<ul>';
            for (var i = 0; i < error.length; i++) {
                el('#errores').innerHTML += '<li>' + error[i] + '</li>';
            }
            el('#errores').innerHTML += '</ul>';
            return enviado;
        })
                .catch(ex => { //Network error
                    el('#errores').innerHTML = "Error en conexión";
                    console.error("Error en conexión");
                    return enviado;
                });
    }
}

window.addEventListener('load', () => {
//Create and initialize controller
    window.ctrl = new LibrosCtrl();
    console.log('Inicializando controlador libros');
    ctrl.init();
});
        