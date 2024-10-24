import axiosInstance from "../axiosInstance";


const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';


class DashboardServices  {

    getAllProblems() {
        return axiosInstance.get(`${LEETCODE_BASE_URL}/coding-problems/problems`)
    }

    getProblemsByCategory(category) {
      return axiosInstance.get(`${LEETCODE_BASE_URL}/coding-problems/category/${category}`)
  }
  getProblemsByDifficulty(difficulty) {
    return axiosInstance.get(`${LEETCODE_BASE_URL}/coding-problems/difficulty/${difficulty}`)
}

getProblemById(id) {
  return axiosInstance.get(`${LEETCODE_BASE_URL}/coding-problems/problem/${id}`)
}

    getProductDetails(productId){
      return axiosInstance.get(`${LEETCODE_BASE_URL}/dashboard/getProductDetails`,{
        params:{
          productId:productId
        }
      })
    }
    

  getAllCategories(){
    return axiosInstance.get(`${LEETCODE_BASE_URL}/dashboard/getCategories`)
  }

  getProductsByCategoryName(category){
    return axiosInstance.get(`${LEETCODE_BASE_URL}/dashboard/getProductsByCategory`,{
      params:{
        category:category
      }
    });
  }

  getLocationDetails(username){
    return axiosInstance.get(`${LEETCODE_BASE_URL}/dashboard/getLocation`, {
      params: {
        username: username
      }
    });
  }

  addVehicleDetails(userId, vehicle){
    return axiosInstance.post(`${LEETCODE_BASE_URL}/dashboard/addVehicleInfo/${userId}`,vehicle)
  }
  
  

}

export default new DashboardServices();