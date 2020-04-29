
class LibrosController {
    constructor($http) {

        this.$http = $http;
        this.serviceUrl = 'api/libros';
        
        //init view-model
        this.visualiza = true;
        this.libros=[];
        this.cargaLibros();
        this.libro = {ISBN: "", titulo: "", fecha: "XXXX", precio: 0};
    }

    altaLibro() {
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
    
    visualizaForm(){
        this.visualiza = true;
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
