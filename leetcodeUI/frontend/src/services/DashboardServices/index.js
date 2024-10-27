import axiosInstance from "../axiosInstance";


const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';


class DashboardServices  {

    getAllProblems() {
        return axiosInstance.get(`${LEETCODE_BASE_URL}/coding-problems/problems`)
    }

    executeCode(body, id) {
      return axiosInstance.post(`${LEETCODE_BASE_URL}/coding-submission/execute/${id}`, body)
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






}

export default new DashboardServices();