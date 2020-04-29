
class LibrosController {
    constructor($http) {

        this.$http = $http;
        this.serviceUrl = 'api/libros';

        //init view-model
        this.visualiza = true;
        this.editando = false;
        this.libros = [];
        this.cargaLibros();
        this.libro = {ISBN: "", titulo: "", fecha: "XXXX", precio: 0};
    }

    altaLibro() {
        if (this.editando) {
            this.$http.put(this.serviceUrl + '/' + this.libro.ISBN, this.libro)
                    .then(response => {
                        console.log("Libro editado");
                        this.libro = {};
                        this.visualiza = false;
                        this.editando = false;
                        this.errorMsgs = [];
                    }).then(() => this.cargaLibros()
            ).catch(response => { //bean validation errors
                this.errorMsgs = response.data;
            });
        } else {
            this.$http.post(this.serviceUrl, this.libro)
                    .then(response => {
                        console.log("Nuevo libro creado");
                        this.libro = {};
                        this.visualiza = false;
                        this.errorMsgs = [];
                    }).then(() => this.cargaLibros()
            ).catch(response => { //bean validation errors
                this.errorMsgs = response.data;
            });
        }
    }

    cargaLibros() {
        this.$http({
            method: 'GET',
            url: this.serviceUrl
        }).then(response => {
            this.libros = response.data;
        });
    }

    borraLibro(isbn) {
        this.$http.delete(this.serviceUrl + '/' + isbn)
                .then(response => {
                    console.log("Libro borrado");
                    this.errorMsgs = [];
                }).then(() => this.cargaLibros()
        ).catch(response => { //bean validation errors
            this.errorMsgs = response.data;
        });
    }

    visualizaForm() {
        this.visualiza = true;
    }

    editar(isbn) {
        this.editando = true;
        this.$http.get(this.serviceUrl + '/' + isbn)
                .then(response => {
                    console.log("Editar libro");
                    this.libro = response.data;
                    this.errorMsgs = [];
                }).then(() => this.visualizaForm()
        ).catch(response => { //bean validation errors
            this.errorMsgs = response.data;
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
