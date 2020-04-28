
class LibrosController {
    constructor($http) {

        this.$http = $http;
        this.serviceUrl = 'api/libros';
        //init view-model
        this.cargaLibros();
        this.libro = {ISBN: "", titulo: "", fecha: "XXXX", precio: 0};
    }

    altaLibro() {
        this.libros.push(this.libro);
        this.$http.post(this.serviceUrl, this.libro)
                .then(response => {
                    console.log("Libro enviado");
                    this.libro = {};
                    this.errorMsgs = [];
                }).then(() => this.cargaLibros()
        ).catch(response => { //bean validation errors
            this.errorMsgs = response.data;
        });
    }

    cargaLibros() {
        this.$http({
            method: 'GET',
            url: this.serviceUrl
        }).then(response => {
            this.libros = response.data;
        });
    }
}

LibrosController.$inject = ['$http'];

const appLibrosComponent = {
    templateUrl: './js/app-libros.template.html',
    controller: LibrosController
};

export const AppLibrosModule = angular
        .module("libros.app", ['ngRoute'])
        .component("appLibros", appLibrosComponent)
        .name;
