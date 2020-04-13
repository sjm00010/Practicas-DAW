function validarFormulario(event) {
    console.log("Validando alta desde JS");
    let valido = true;
    let el = selector => document.querySelector(selector);
    let isbn = el('#form\\:isbn').value;
    let titulo = el('#form\\:titulo').value;
    
    // Validación
    if (isbn.search(/\d{10}(\d{3})?/) === -1 && isbn.length !== 10 || isbn.length !== 13 ) {
        el('#errIsbn').textContent = "El ISBN debe tener 10 o 13 dígitos";
        valido = false;
    }else{
        el('#errIsbn').textContent = "";
    }
    if (titulo.length < 2 || titulo.length > 25) {
        el('#errTitulo').textContent = "La longitud del título debe estar entre 2 y 25 caracteres";
        valido = false;
    }else{
        el('#errTitulo').textContent = "";
    }
    
    // Cancilacion del submit en caso de error
    if(!valido)
        event.preventDefault(); //stop form submit
}