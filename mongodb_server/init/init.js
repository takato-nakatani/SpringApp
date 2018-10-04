db.auth( test_user, test_user )
use mongodb-sample
var newUsers = [
  {
        user: 'test_user',
        pwd: 'test_user',
        roles: [
            {
                role: 'userAdminAnyDatabase',
                db: 'sample_db'
            },
		  "readWriteAnyDatabase"
        ]
    }
];

var currentUsers = db.getUsers();
if (currentUsers.length === newUsers.length) {
    quit();
}
db.dropAllUsers();

for (var i = 0, length = newUsers.length; i < length; ++i) {
    db.createUser(newUsers[i]);
}
