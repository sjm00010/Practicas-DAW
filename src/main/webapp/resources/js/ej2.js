function validarFormulario(event) {
//    console.log("Ejecutando manejador");
    let dni = $('#valida\\:dni').val();
    if(dni != ''){
        console.log(dni);
    }
    if (dni.search(/^\d{8}[A-Z]$/) === -1) {
        $('#respuesta').text("DNI no válido (12345678A)");
    }else{
        $('#respuesta').text("DNI válido");
    }
}