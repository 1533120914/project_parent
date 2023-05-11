// 导入axios
import axios from 'axios';
// 导入加载状态组件 - 用于在每次请求之前显示加载状态
import { Loading } from "element-ui";
// 导入消息组件 - 用于在请求出错的时候弹出错误消息
import { Message } from 'element-ui';
// 创建axios实例, 并命名为http
const http = axios.create({
    // 后端的基础URL地址
    baseURL: 'http://localhost:8888/',
    // 设置请求头 - 用于POST请求,如果不设置,那么POST请求后端无法取到数据
    headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
    // 设置请求超时时长 - 如果15秒钟后端没有响应, 请求就结束
    timeout: 15000,
    // 是否携带Cookie
    // withCredentials:true
})
// 定义一个全局的loading变量, 用于存储loading对象
let loading;
// 设置请求的前置拦截器
http.interceptors.request.use(
    (config) => {
        // 在每次请求发送之前显示加载状态
        loading = Loading.service({
            lock: true,
            text: "加载中...", // 加载状态上的文字
            spinner: "el-icon-loading", // 加载状态上的图标
            background: "rgba(0,0,0,0.2)" // 加载状态的背景颜色
        });
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)
// 设置请求的后置拦截器
http.interceptors.response.use(
    (response) => {
        loading.close(); // 关闭加载状态
        let data = response.data // 获取请求体中的数据
        if(data.code === '000000') { // 请求成功
            if (data.data) { // 判断是否有数据返回
                return data.data // 把数据交给Axios
            }else { // 如果没有数据返回, 显示成功消息
                Message.success({
                    showClose: true,
                    message:data.msg
                })
            }
        }else {
            Message.error({
                showClose:true,
                message:`${data.code}:${data.msg}`
            })
            return Promise.reject(data)
        }
    },
    (error) => { // http请求失败
        loading.close();
        if (error && error.response) {//对各种响应码做提示，也可进行其它操作
            switch (error.response.status) {
                case 404:
                    Message.error({
                        showClose:true,
                        message:'404:找不到资源!'
                    })
                    break
                case 500:
                    Message.error({
                        showClose:true,
                        message:'500:服务器出错!'
                    })
                    break
                default:
                    Message.error({
                        showClose:true,
                        message:'请求失败,请稍后再试'
                    })
            }
        } else {
            Message.error({
                showClose:true,
                message:'检查网络'
            })
        }
        return Promise.reject(error)
    }
)
// 导出http对象
export default http;