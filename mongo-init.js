db.createUser({
    user: "kouskefil",
    pwd: "kouskefil123",
    roles: [
        { role: "readWrite", db: "employee" }
    ]
});