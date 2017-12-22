const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });



// The Firebase Admin SDK to access the Firebase Realtime Database. 
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

//const rootRef=admin.database().ref();
const nodemailer=require('nodemailer');
const contacts = [];
const children = [];
const mailTransport = nodemailer.createTransport({
    host:'smtp.gmail.com',
    port:465,
    secure:true,
    auth:{
        user: 'vinitramk@gmail.com',
        pass: 'vinitramk3097'
    }
})


// Take the text parameter passed to this HTTP endpoint and insert it into the
// Realtime Database under the path /messages/:pushId/original
exports.addMessage = functions.https.onRequest((req, res) => {
  // Grab the text parameter.
  const original = req.query.text;
  // Push the new message into the Realtime Database using the Firebase Admin SDK.
  admin.database().ref('/Underfive/ImmRec/messages').push({today: false}).then(snapshot => {
      admin.database().ref('/Underfive/GenRec/messages').push({today:false});
      res.redirect(303,snapshot.ref);
  })
})

exports.collectChildren= functions.database.ref('/Underfive/ImmRec').onWrite(event => {
  admin.database().ref().child('/Underfive/ImmRec').once('value')
    .then(snap => {
        console.log('ImmRec','Hello');
        snap.forEach(childSnap => {
            const childid = childSnap.val().childid;
            children.push(childid);
        })
        return children 
    }).then(children => {
        console.log('Child ids: '+children.join());
        admin.database().ref().child('/Underfive/GenRec').once('value')
        .then(snap => {
            snap.forEach(childSnap => {
                const childid=childSnap.val().childid;
                const phoneno=childSnap.val().mob;
                if(children.indexOf(childid)!=-1) {
                    contacts.push(phoneno);
                }
            })
            return contacts
        }).then(contacts => {
            console.log('Contacts: '+contacts.join());
            /*admin.database().ref().child('/Underfive/ImmRec/messages').remove();
            admin.database().ref().child('/Underfive/GenRec/messages').remove();*/
        })
    })
})


