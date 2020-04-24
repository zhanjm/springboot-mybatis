jQuery(function(response){
    $.ajax({url:"findall",success:function(result){
            var vm = new Vue({
                el:"#app2",
                data: {
                    tableData: result
                }
            })
        }});

});





