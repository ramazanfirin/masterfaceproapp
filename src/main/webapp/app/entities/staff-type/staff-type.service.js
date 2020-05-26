(function() {
    'use strict';
    angular
        .module('masterfaceproApp')
        .factory('StaffType', StaffType);

    StaffType.$inject = ['$resource'];

    function StaffType ($resource) {
        var resourceUrl =  'api/staff-types/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
