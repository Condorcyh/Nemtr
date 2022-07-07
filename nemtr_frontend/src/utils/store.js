const LOCAL_STORAGE_SCHOOL_KEY = "searchSchoolHistory";

class Store { }

Store.saveSchoolHistory = (arr) => {
    localStorage.setItem(LOCAL_STORAGE_SCHOOL_KEY, JSON.stringify(arr));
}

Store.loadSchoolHistory = () =>JSON.parse(localStorage.getItem(LOCAL_STORAGE_SCHOOL_KEY))

Store.removeAllSchoolHistory=()=>{localStorage.removeItem(LOCAL_STORAGE_SCHOOL_KEY)}

// module.exports = Store
export default Store;
