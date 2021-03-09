import axios from 'axios'

const AXIOS_INSTANCE = axios.create({
  baseURL: "http://localhost:8080"
})

export default AXIOS_INSTANCE