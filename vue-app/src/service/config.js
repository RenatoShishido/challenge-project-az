import axios from 'axios'

const AXIOS_INSTANCE = axios.create({
  baseURL: "http://localhost:3000"
})

export default AXIOS_INSTANCE