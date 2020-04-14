$(() => {
    window.ctrl = new librosCtrl(); //Register global var
    ctrl.init(); //Attach view event Handlers
});


class librosCtrl {
    constructor() {
        this.config = {
            formulario: "#alta",
            isbn: "#alta\\:isbn",
            titulo: "#alta\\:titulo",
            fecha: "#alta\\:fecha",
            precio: "#alta\\:precio"
        };
    }

    init() {
        $(this.config.formulario)
                .on('submit', event => { //ev. handler
                    if (this.validarDatos() === false) {
                        //Stop submission
                        event.preventDefault();
                    };
                });
    }

    validarDatos() {
        console.log("Validando alta desde JS");
        let el = selector => document.querySelector(selector);
        let valido = true;
        var isbn = $(this.config.isbn).val();
        let titulo = $(this.config.titulo).val();
        let fecha = $(this.config.fecha).val();
        let precio = $(this.config.precio).val();

        // Validación
        if (isbn.search(/^\d{10}(\d{3})?$/) === -1) {
            el('#errIsbn').textContent = "El ISBN debe tener 10 o 13 dígitos";
            valido = false;
        } else {
            el('#errIsbn').textContent = "";
        }
        if (titulo.length < 2 || titulo.length > 25) {
            el('#errTitulo').textContent = "La longitud del título debe estar entre 2 y 25 caracteres";
            valido = false;
        } else {
            el('#errTitulo').textContent = "";
        }
        if (fecha.length !== 4) {
            el('#errFecha').textContent = "El año debe tener el formato YYYY";
            valido = false;
        } else {
            el('#errFecha').textContent = "";
        }
        if (precio <= 0) {
            el('#errPrecio').textContent = "El precio debe ser igual o mayor a 0";
            valido = false;
        } else {
            el('#errPrecio').textContent = "";
        }

        return valido;
    }
}

// ejercicio 4
function borrar(event){ 
    console.log("Confirma borrado");
    let opt = confirm("¿Estas seguro de borrar el libro?");
    console.log(opt);
    if (opt === false){
        console.log("Cancelado");
        event.preventDefault();
    }
}