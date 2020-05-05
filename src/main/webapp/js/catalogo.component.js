export const catalogoComponent = {
    bindings: {
        datos: '<',
        seleccion: '='
    },
    template: `
    <h1>Listado de libros</h1>
    <ul class="list-group" id="idLibros">
        <li class="list-group-item" ng-repeat='l in $ctrl.datos'>
            <form class="my-0">
                <strong>{{l.ISBN}}</strong> : {{l.titulo}}
                <button class="btn-sm btn-primary" ng-click='$ctrl.seleccion = l'>
                    Editar</button>
            </form>
        </li>
    </ul>`
};


