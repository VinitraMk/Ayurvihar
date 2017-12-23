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

var childRef = admin.database().ref('/Underfive/ImmRec');

var request = require('request');


// Take the text parameter passed to this HTTP endpoint and insert it into the
// Realtime Database under the path /messages/:pushId/original
exports.addMessage = functions.https.onRequest((req, res) => {
  // Grab the text parameter.
  const original = req.query.text;
  // Push the new message into the Realtime Database using the Firebase Admin SDK.

  admin.database().ref('/Underfive/ImmRec/messages').push({today: false}).then(snapshot => {
      admin.database().ref('/Underfive/GenRec/messages').push({today:false});
      admin.database().ref('/Underfive/Intervals/messages').push({today:false});
      res.redirect(303,snapshot.ref);
  })
})

exports.collectChildren= functions.database.ref('/Underfive/ImmRec/messages/{messageId}').onCreate(event => {

    //current date and next date
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate()+1);
    var d = tomorrow.getDate();
    var m = tomorrow.getMonth()+1;
    var y = tomorrow.getFullYear();

    if(d<10) {
        d = '0'+d;
    }
    if(m<10) {
        m = '0'+m;
    }

    tomorrow = d + '-' + m + '-' + y;

    var today = new Date();
    var currdate = new Date();
    d = today.getDate();
    m = today.getMonth()+1;
    y = today.getFullYear();

    if(d<10) {
        d = '0'+d;
    }
    if(m<10) {
        m = '0'+m;
    }

    today = d + '-' + m + '-' + y;


    admin.database().ref().child('/Underfive/Intervals').once('value') 
    .then(snap => {
        var intervals = {
            bcg:snap.val().bcg,
            dpv0:snap.val().dpv0,
            hbv0:snap.val().hbv0,
            dptopv1:snap.val().dptopv1,
            dptopv2:snap.val().dptopv2,
            dptopv3:snap.val().dptopv3,
            hbv1:snap.val().hbv1,
            hbv2:snap.val().hbv2,
            hbv3:snap.val().hbv3,
            mv1:snap.val().mv1,
            mmr:snap.val().mmr,
            dobv2:snap.val().dobv2,
            v3:snap.val().v3,
            v4:snap.val().v4,
            v5:snap.val().v5,
            v6:snap.val().v6,
            v7:snap.val().v7,
            v8:snap.val().v8,
            dv9:snap.val().dv9
        };
        snap.forEach(childSnap => {
            if(childSnap.key=='messages') 
                childSnap.ref.remove();
        })
        return intervals;
    }).then( intervals => {

        console.log('Intervals',intervals);
            
      admin.database().ref().child('/Underfive/ImmRec').once('value')
        .then(snap => {
            snap.forEach(childSnap => {
                if(childSnap.key!='messages') {
                    var f=0;
                    var idkey = childSnap.val().childid;
                    childSnap.forEach(grandChildSnap => {
                        const item=grandChildSnap.val();
                        var ch = grandChildSnap.key;
                        //Checking for daily updates
                        if(item!=idkey && ch[0]=='d' && item!="") {

                            var vname = ch.substring(1,ch.length);
                            var split = item.split('-');
                            console.log('item',item,split.join());
                            var dd = parseInt(split[0]);
                            var mm = parseInt(split[1]);
                            var yy = parseInt(split[2]);
                            var d1 = new Date(yy,(mm-1),dd);
                            var diff = (d1-currdate);

                            console.log('comparedates: '+diff);
                            if(diff<0) {
                                //Building date object
                                //var nd = new Date(dd,mm-1,yy);
                                console.log('date',d1);
                                console.log('helokey',ch,typeof(ch));
                                var inte = intervals[vname];
                                console.log('hello',inte);

                                //incrementing by interval value
                                d1.setDate(d1.getDate()+inte);
                               
                                //Converting back to date string
                                var td = d1.getDate();
                                var tm = d1.getMonth()+1;
                                var ty = d1.getFullYear();

                                if(td<10) 
                                    td = '0' + td;

                                if(tm<10) 
                                    tm = '0' + tm;

                                var nds = td + '-' + tm + '-' + ty;
                                console.log('newdate',nds);

                                childRef.child('/'+childSnap.key+'/'+ch).set(nds);
                            }
                        }
                        if(item==tomorrow) {
                            f=1;
                        }
                    })
                    if(f==1) {
                        const childid = childSnap.val().childid;
                        children.push(childid);
                    }
                }
                else {
                    childSnap.ref.remove();
                }
            })
            return children 
        }).then(children => {
            //console.log('Child ids: '+children.join());
            admin.database().ref().child('/Underfive/GenRec').once('value')
            .then(snap => {
                snap.forEach(childSnap => {
                    if(childSnap.key!='messages') {
                        const childid=childSnap.val().childid;
                        const phoneno=childSnap.val().mob;
                        if(children.indexOf(childid)!=-1) {
                            contacts.push(phoneno);
                        }
                    }
                    else {
                        childSnap.ref.remove();
                    }
                })
                return contacts
            }).then(contacts => {
                console.log('Contacts Extracted');
            })
        })
    })
})



