
class LibrosController {
    constructor() {
        //init view-model
        this.libros = [
            {isbn: "1234567890", titulo: "El Ingenioso Hidalgo Don Quijote de la Mancha"},
            {isbn: "1234567891", titulo: "The definitive guide to JSF in Java EE 8"},
            {isbn: "1234567892", titulo: "Naruto, Season 1"},
        ];
        this.libro = {};
    }
}

const appLibrosComponent = {
    templateUrl: './js/app-libros.template.html',
    controller: LibrosController
};

export const AppLibrosModule = angular
        .module("libros.app", [])
        .component("appLibros", appLibrosComponent)
        .name;
