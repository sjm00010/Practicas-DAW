
class LibrosController {
    constructor($http) {

        this.$http = $http;
        this.serviceUrl = 'api/libros';
        //init view-model
        this.libros = [
            {isbn: "1234567890", titulo: "El Ingenioso Hidalgo Don Quijote de la Mancha"},
            {isbn: "1234567891", titulo: "The definitive guide to JSF in Java EE 8"},
            {isbn: "1234567892", titulo: "Naruto, Season 1"}
        ];
        this.libro = {isbn: "", titulo: ""};
    }

    altaLibro() {
        this.libros.push(this.libro);
        this.libro = {};
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
