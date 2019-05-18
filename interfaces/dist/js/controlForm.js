/* controle des champs des formulaires*/

$().ready(function() {

  $("#testForm").validate({
    rules : {
      nom : {
        required : true,
        minlength: 3
      }
    },
    messages : {
      nom : "Veuillez fournir un nom d'au moins trois lettres"
    }
 });

  $("#inscriptionEnseignantsForm").validate({
    rules : {
      matricule : {
        required : true
      },
      nom : {
        required : true,
        minlength: 3
      },
      prenom : {
        required : true,
        minlength: 3
      },
      sexe : {
        required : true
      },
      date_naissance : {
        required : true,
        /*regex: /^(0[1-9]|[12][0-9]|3[01])+\/(0[1-9]|1[012])+\/(19|20)\d\d/,*/
        minlength: 5
      },
      email : {
        required : true,
        regex: /^[A-Za-z0-9_]+\@[A-Za-z0-9_]+\.[A-Za-z0-9_]+/ //verification grace a regex
      },
      telephone : {
        minlength: 6
      },
      adresse : {

      }
    },
    messages : {
      nom : "Veuillez fournir un nom d'au moins trois lettres",
      prenom: "Veuillez fournir un prénom d'au moins trois caractères ",
      matricule: "Veuillez fournir un matricule valide ",
      sexe: "Veuillez renseigner votre sexe ",
      date_naissance: "Veuillez renseigner une date valide ",
      mdp: "Veuillez renseigner un mot de passe",
      cmdp: "Veuillez confirmer le mot de passe",
      email: "Veuillez renseigner une adresse mail valide ",
      telephone: "Veuillez renseigner un numéro de téléphone valide ",
      adresse: "Veuillez renseigner une adresse valide "
      
    }
 });

$.validator.addMethod(
"regex",
function(value, element, regexp) {
var re = new RegExp(regexp);
return this.optional(element) || re.test(value);
},
"Please check your input."
);

/* controle de la date de naissance*/
$('#date_nais').keyup(function(){
var nb_car = $(this).val().length;
var maximum = 10; 
if (nb_car > maximum) {
    $(this).val($(this).val().substring(0,10));
}
else{

    if(nb_car == 2 || nb_car == 5) {
    $(this).val($(this).val()+'/');
    }    
}
});


});