# Ejercicio de comandos con MongoDB

Crea una base de datos llamada centro_estudios
 
## 1. Crea una base de datos llamada centro_estudios 
2. Crea dos colecciones, una llamada alumnos y otra llamada profesores 

````mongosh	
    use centro_estudios
    db.createCollection("alumnos")
    db.crreateCollection("profesores")
````

3. En la colección llamada alumnos, inserta los siguientes datos: 

![Creación de alumnos](./pantallazo_1.jpg)

````mongosh
db.alumnos.insertMany([{
        "_id": "672478d2f7d96a9e53a59004",
        "rating": 5.27,
        "age": 34,
        "name": "Kate Neal",
        "gender": "female",
        "email": "kateneal@hivedom.com",
        "phone": "+1 (825) 464-3051",
        "calificaation": 2,
        "higher_grade": "DAW"
    },
    {
        "_id": "672478d2e698286d196506ab",
        "rating": 9.24,
        "age": 37,
        "name": "Virginia Flowers",
        "gender": "female",
        "email": "virginiaflowers@hivedom.com",
        "phone": "+1 (949) 562-2204",
        "calificaation": 1,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d21e269b4a57c5e051",
        "rating": 7.12,
        "age": 33,
        "name": "Vanessa Gibbs",
        "gender": "female",
        "email": "vanessagibbs@hivedom.com",
        "phone": "+1 (942) 591-3521",
        "calificaation": 5,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d29c4cea1c323ee84d",
        "rating": 6.02,
        "age": 26,
        "name": "Wood Sloan",
        "gender": "male",
        "email": "woodsloan@hivedom.com",
        "phone": "+1 (990) 563-3488",
        "calificaation": 3,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d20bb94d3414d5105a",
        "rating": 5.17,
        "age": 26,
        "name": "Josefina Berger",
        "gender": "female",
        "email": "josefinaberger@hivedom.com",
        "phone": "+1 (896) 489-2692",
        "calificaation": 0,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d25ada5a95d4bfb81e",
        "rating": 8.81,
        "age": 34,
        "name": "Jaime Howard",
        "gender": "female",
        "email": "jaimehoward@hivedom.com",
        "phone": "+1 (894) 510-2219",
        "calificaation": 7,
        "higher_grade": "ASIR"
    },
    {
        "_id": "672478d2b66e5b36013188b5",
        "rating": 5.56,
        "age": 38,
        "name": "Chase Conley",
        "gender": "male",
        "email": "chaseconley@hivedom.com",
        "phone": "+1 (937) 488-2414",
        "calificaation": 10,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d21456f136d4e116f",
        "rating": 6.12,
        "age": 23,
        "name": "Dixie Woods",
        "gender": "female",
        "email": "dixiewoods@hivedom.com",
        "phone": "+1 (976) 408-3974",
        "calificaation": 4,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d2b7dd115e7193f58f",
        "rating": 8.81,
        "age": 39,
        "name": "Moody Arnold",
        "gender": "male",
        "email": "moodyarnold@hivedom.com",
        "phone": "+1 (830) 420-2446",
        "calificaation": 6,
        "higher_grade": "DAM"
    },
    {
        "_id": "672478d242d0b4abb287d0fa",
        "rating": 7.17,
        "age": 23,
        "name": "Mcmillan Singleton",
        "gender": "male",
        "email": "mcmillansingleton@hivedom.com",
        "phone": "+1 (904) 563-3920",
        "calificaation": 10,
        "higher_grade": "DAW"
    },
    {
        "_id": "672478d202650f5e0185632a",
        "rating": 7.55,
        "age": 34,
        "name": "Wall Best",
        "gender": "male",
        "email": "wallbest@hivedom.com",
        "phone": "+1 (893) 461-3843",
        "calificaation": 0,
        "higher_grade": "ASIR"
}])
````

4. En la colección profesores inserta los siguientes datos

 ````mongosh
db.insertMany([ 
    { 
        "_id": "6724775549c221e4ea993a3e", 
        "rating": 9.02, 
        "age": 20, 
        "name": "Victoria Foster", 
        "gender": "female", 
        "email": "victoriafoster@hivedom.com", 
        "phone": "+1 (814) 589-2100", 
        "subjects": [ 
            "Lenguaje de marcas", 
            "Sistemas informáticos", 
            "Base datos" 
        ], 
        "title": "Ingeniero indistriales" 
    }, 
    { 
        "_id": "672477553713dad19d93638f", 
        "rating": 6.39, 
        "age": 25, 
        "name": "Hudson Gates", 
        "gender": "male", 
        "email": "hudsongates@hivedom.com", 
        "phone": "+1 (997) 459-3540", 
        "subjects": [ 
            "Base datos", 
            "Sistema de gestion empresarial", 
            "Sistema de gestion empresarial", 
            "Base datos" 
        ], 
        "title": "Ingeniero informatico" 
    }, 
    { 
        "_id": "6724775559a0f8f473a32640", 
        "rating": 9.93, 
        "age": 27, 
        "name": "Elisa Mcgowan", 
        "gender": "female", 
        "email": "elisamcgowan@hivedom.com", 
        "phone": "+1 (932) 507-2855", 
        "subjects": [ 
            "Lenguaje de marcas", 
            "Lenguaje de marcas", 
            "Programacion multimedia", 
            "Sistema de gestion empresarial", 
            "Acceso datos", 
            "Base datos" 
        ], 
        "title": "Ingeniero indistriales" 
    }, 
    { 
        "_id": "6724775595741c4ccac71f7b", 
        "rating": 7.24, 
        "age": 37, 
        "name": "Rena Castro", 
        "gender": "female", 
        "email": "renacastro@hivedom.com", 
        "phone": "+1 (851) 423-3547", 
        "subjects": [ 
            "Sistemas informáticos", 
            "Sistemas informáticos", 
            "Acceso datos", 
            "Programacion multimedia" 
        ], 
        "title": "Ingeniero indistriales" 
    }, 
    { 
        "_id": "67247755ce71f04e8c1c3e1d", 
        "rating": 8.08, 
        "age": 32, 
        "name": "Haney Price", 
        "gender": "male", 
        "email": "haneyprice@hivedom.com", 
        "phone": "+1 (982) 579-3098", 
        "subjects": [ 
            "Sistema de gestion empresarial", 
            "Lenguaje de marcas", 
            "Desarrollo interfaces" 
        ], 
        "title": "Ingeniero informatico" 
    }, 
    { 
        "_id": "672477552b252c8a32223894", 
        "rating": 8.48, 
        "age": 24, 
        "name": "Bernice Hall", 
        "gender": "female", 
        "email": "bernicehall@hivedom.com", 
        "phone": "+1 (937) 419-2861", 
        "subjects": [ 
            "Acceso datos", 
            "Acceso datos", 
            "Base datos", 
            "Desarrollo interfaces", 
            "Acceso datos", 
            "Programacion multimedia" 
        ], 
        "title": "Ingeniero caminos" 
    } 
    ])
````

5. Obtener todos los alumnos que están matriculados en DAM
 
 ````mongosh
 db.alumnos.find({higher_grade: "DAM"})
````

 
6. Obtener todos los alumnos que tienen más de 20 años

````mongosh
db.alumnos.find({age:{$gt:20}})
````


7. Obtener todos los profesores que imparten la asignatura de Programación

````mongosh
db.profesores.find({subjects:"Programacion multimedia"})
````

![Creación de alumnos](./pantallazo_2.jpg)

8. Obtener todos los profesores que son ingenieros informáticos

````mongosh
db.profesores.find({title: "Ingeniero informatico"})
````

9. Obtener todos los profesores que tienen mensos de 40 años y más de 30

````mongosh
db.profesores.find({age:{$lt:40,$gt:30}})
````

10.  Obtener el profesor mejor valorado

````mongosh
db.profesores.find().sort({ rating: -1 }).limit(1)
````


11.   Obtener el profesor que más asignaturas imparte

````mongosh
db.profesores.find().sort({ subjects: 1 }).limit(1)
````


12.  Actualizar la edad del alumno cuyo correo es aprilmanning@proflex.com a 32

April no está en la base de datos, así que se ha actualizado a Vanessa para el ejercicio.

````mongosh
db.alumnos.updateOne({email:"aprilmanning@proflex.com"},{$set:{age:32}})
````

![imagen](./pantallazo_3.jpg)

13.   Actualizar la edad de todos los alumnos del ciclo de DAM en un año

````mongosh
db.alumnos.updateMany({higher_grade: "DAM"}, {$inc:{age:1}})
````


14.  Actualizar todos los alumnos y añade el campo FCTs puesto como true

````mongosh
db.alumnos.updateMany({},{$set:{FCTs: true}})
````

15.  Actualiza todos los alumnos documentos que tengan una nota inferior a 5 y pon las FCTs como false

````mongosh
db.alumnos.updateMany({calificaation:{$lt:5}},{$set:{FCTs:false}})
````

16.  Elimina todos aquellos registros de los alumnos que tenga las FCTs como false

````mongosh
db.alumnos.deleteMany({FCTs:false})
````


17.  Elimina todos aquellos profesores que tienen una calificación entre 1 y 4

````mongosh
db.profesores.deleteMany({rating: {$gte:1, $lte:4}})
````

![imagen](./pantallazo_4.jpg)


