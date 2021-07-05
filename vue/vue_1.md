[TOC]

# 1. Vue 初体验

* 声明式编程

* 响应式的结构

* 浏览器执行流程

  按行执行，执行到 ```js``` 部分，会自动按照 ```Vue``` 实例的说明对原 ```HTML``` 进行解析和修改。

## 1.1  v-for 列表

```html
<div id="app">
    <ul>
        <!--理解为 for item in 容器-->
        <li v-for="item in movies">{{item}}</li>
        <li v-for="(item, index) in movies">{{index}} - {{item}}</li>
    </ul>
</div>

<script src="./js/vue.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    const app = new Vue({
        el: "#app",
        data: {
            movies : ['星际穿越', '大话西游']
        }
    });
</script>
```

* 响应式
console 输入 app.movies.push('海王')，回车，页面也会随之刷新

## 1.2  v-on 监听事件

> v-on:XXX="函数名" 
>
> 语法糖：@click="函数名"

> Vue({methods:{}})

## 1.3  Vue 实例的 options

* el: string | HTMLElement

  该实例管理哪个 DOM

* data： Object | Function

  数据对象

* methods：{[key: string]: Function}

  定义方法

## 1.4  Vue 的生命周期

《假设此处有张图》



# 2.  模板标签

## 2.1  v-once

* 后面无表达式

* 被标记元素只被渲染一次

## 2.2  v-html

解析 ```html``` 格式的内容，并显示

```hjjtml
<div id="app">
    <h2>{{url}}</h2>
    <h2 v-html="url"></h2>
</div>

<script src="./js/vue.min.js"></script>
<script>
	const app = new Vue({
		el: "#app",
		data: {
			message: "你好",
	        url: "<a href='https://www.baidu.com'>百度一下</a>"
    	    }
        })
</script>
```

## 2.3  v-text

* 将数据显示在页面中，接受 string 类型

* 与 ```{{}}``` 类似

## 2.4  v-pre

用于跳过这个元素和它的子元素的渲染（编译），显示原本的内容

## 2.5  v-cloak

vue 解析前，元素中有这个属性；解析后，元素中无该属性。

## 2.6  v-bind

* 动态绑定属性

```html
<div id="app">
    <a v-bind:href="url">百度一下</a>
    <!--语法糖-->
    <a :href="url">百度一下</a>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {
            message: "你好",
            url: "https://www.baidu.com"
        }
    })
</script>
```

### 2.6.1  动态绑定 class （对象语法）

```html
<style>
    .active{
        color: red;
    }
</style>
<div id="app">
    <!--<h2 v-bind:class="{类名1: true，类名2：false  ....}">你好</h2>-->
    <h2 v-bind:class="{active: isActive}">你好</h2>
    <button type="button" v-on:click="btn">让我变色</button>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue ({
        el: "#app",
        data: {
            isActive: true,
        },
        methods: {
            btn: function () {
                this.isActive = !this.isActive;
            }
        }
    })
</script>
```

### 2.6.2  动态绑定 class （数组语法）

### 2.6.3  动态绑定 style（对象语法）

> <!--<h2 v-bind:style="{属性名: 属性值，属性名: 属性值  ....}">你好</h2>-->

### 2.6.2  动态绑定 style（数组语法）



# 3.  计算属性--computed

> Vue({computed:{}})



```html
<div id="app">
	<h2>总价为：{{totalPrice}}</h2>
</div>

<script>
	const app = new Vue ({
		el: "#app",
		data: {
			books: [ {price: 1}, {price: 1}, {price: 1}, {price: 1}, ]
		},
		computed: {
			totalPrice: function () {
				let result = 0;
				for(let book of this.books)
					result += book['price'];
				return result;
			}
		}
	})
</script>
```



## 3.1  计算属性的本质（见注释）

```html
<div id="app">
	<h2>你好, {{fullName}}</h2>
</div>

<script>
	const app = new Vue ({
		el: "#app",
		data: {
			firstName: "Chenyu",
			lastName: "Wu"
		},
		computed: {
			fullName: function () {
				return this.firstName + " " + this.lastName;
			}
            // 完整写法：计算属性的本质
			// 计算属性一般无 set 方法，一般只作为只读属性
			// fullName: {
			// 	set: function () {},
			// 	get: function () {
			// 		return this.firstName + " " + this.lastName;
			// 	}
			// }
		}
	})
</script>
```



# 4.  ES6 补充

## 4.1  let/var

* let 块级作用域

* const

  *  被修饰的标识符不会再被赋值
  * 如果标识符指向一个对象，那么这个标识符就不能指向其他对象，但该对象內部的属性是可以被修改的
  * 被修饰的标识符在声明必须被赋值

  

## 4.2  增强写法

### 4.2.1  对象字面量的增强写法

```html
<script>
	const name = "Tom";
	const age = 18;
	const height = 1.88

	// ES5 的写法
	// const obj = {
	// 	name: name,
	// 	age: age,
	// 	height: height
	// };
	// console.log(obj);

	const obj = {
		name,
		age,
		height
	};
	console.log(obj);
</script>
```

### 4.2.2  函数的增强写法

```html
<script>
	// ES5 的写法
	// const obj = {
	// 	run: function () {},
	// 	eat: function () {}
	// };

	const obj = {
		run() {},
		eat() {}
	};
</script>
```



# 5.  事件监听 v-on

```html
<div id="app">
    <h2>{{counter}}</h2>
    <!-- <button v-on:click="increment">+</button>-->
    <!-- <button v-on:click="decrement">-</button>-->
    <!-- 语法糖-->
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
</div>

<script>
    const app = new Vue ({
        el: "#app",
        data: {
            counter: 0
        },
        methods: {
            increment() {this.counter ++;},
            decrement() {this.counter --;}
        }
    });
</script>
```

## 5.1  参数传递

```html
<div id="app">
  <!--1.事件调用方法无参-->
  <button @click="btn1()">按钮1</button>

  <!--2.方法定义时需要一个参数。调用时省略，
      这时，Vue 默认的将浏览器生产的 event 事件对象最为参数传入到方法中-->
  <!--<button @click="btn2">按钮2</button>--> <!--打印：'event 事件对象'-->
  <!--<button @click="btn2()">按钮2</button>--> <!--打印：btn2 undefined-->
  <button @click="btn2('Vue is fun!')">按钮2</button>

  <!--3.方法定义时需要多个参数：event 对象、其他参数。
        调用时，手动获取 event 对象：$event -->
  <!--<button @click="btn2">按钮2</button>--> <!--打印：'event 事件对象'-->
  <!--<button @click="btn2()">按钮2</button>--> <!--打印：btn2 undefined-->
  <button @click="btn3('Vue is fun!', $event)">按钮3</button>
</div>

<script>
  const app = new Vue ({
    el: "#app",
    methods: {
      btn1() {console.log('btn1');},
      btn2(param1) {console.log('btn2', param1);},
      btn3(param1, event) {console.log(param1, "---", event);}
    }
  });
</script>
```

## 5.2  v-on 修饰符的使用

Vue 提供一些修饰符，方便处理一些事件：

<table>
    <tbody>
        <tr>
        	<td>.stop</td>
            <td>调用 event.stopPropagation()</td>
            <td>阻止事件冒泡</td>
        </tr>
        <tr>
        	<td>.prevent</td>
            <td>调用 event.preventDefault()</td>
            <td></td>
        </tr>
        <tr>
        	<td>.{keyCode | keyAlias}</td>
            <td>当事件为特定键触发时，才触发回调</td>
            <td></td>
        </tr>
        <tr>
        	<td>.once</td>
            <td>只触发一次回调</td>
            <td></td>
        </tr>
    </tbody>
</table>

```html
<!--停止冒泡-->
<button click.stop="doThis"></button>
<!--阻止默认行为-->
<button @click.prevent="doThis"></button>
<!--阻止默认行为，没有表达式-->
<form @submit.prevent></form>
<!--串联修饰符―->
<button @click.stop.prevent="doThis"></button>
<!-- 键修饰符，键别名-->
<input @keyup.enter="onEnter">
<!--键修饰符，键代码-->
<input @keyup.13="onEnter">
<!--点击回调只会触发一次-->
<button @click.once="doThis"></button>

```



# 6.  元素显示

## 6.1  v-if 条件判断

```html
<div id="app">
    <h2 v-if="score >= 90">优秀</h2>
    <h2 v-else-if="score >= 80">良好</h2>
    <h2 v-else-if="score >= 70">继续努力</h2>
    <h2 v-else-if="score >= 60">及格</h2>
    <h2 v-else>不及格，请继续努力</h2>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {score: 50},
        methods: {show() {this.isShow = !this.isShow;}}
    })
</script>
```

## 6.2  v-show

```v-show``` 与 ```v-if``` 类似，决定元素是否渲染

## 6.3  二者对比

<table>
    <tr>
    	<td>v-if 为 false</td>
        <td>对应元素不会出现在 DOM 中</td>
        <td>只切换一次，使用 v-if</td>
    </tr>
    <tr>
    	<td>v-show 为 false</td>
        <td>对应元素的 display 属性为 none</td>
        <td>显示与隐藏频繁切换，使用 v-show</td>
    </tr>
</table>





# 7.  小案例--登陆切换

```html
<div id="app">
    <span v-if="isUser">
        <label for="username">用户账号</label>
        <input type="text" id="username" key="username">
    </span>

    <span v-else>
        <label for="email">用户邮箱</label>
        <input type="text" id="email" key="email">
    </span>

    <button @click="isUser = !isUser">切换类型</button>
</div>

<script src="./js/vue.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    const app = new Vue({
        el: "#app",
        data: {isUser: true}
    })
</script>
```

### 注意：由于 Vue 的虚拟 DOM 引起的一个小问题

* 小问题：

如果我们在有输入内容的情况下，切换了类型，我们会发现文字依然显示之前的输入的内容。但是按道理讲，我们应该切换到另外一个 input 元素中。但在这个 input 元素中，我们并没有输入内容。

为什么会出现这个问题呢？

* 问题解答：

这是因为 Vue 在进行 DOM 渲染时，出于性能考虑，会尽可能的复用已经存在的元素，而不是重新创建新的元素。

在上面的案例中，Vue 内部会发现原来的 input 元素不再使用，于是，它直接被作为 else 中的 input 来使用了。

* 解决方案：

如果我们不希望 Vue 出现类似重复利用的问题，可以给对应的 input 添加 key，并且我们需要保证key的不同。



# 8.  循环遍历 v-for

## 8.1  遍历数组

```html
<div id="app">
    <!--第一种-->
    <ul>
        <li v-for="item in programLanguage">{{item}}</li>
    </ul>
    <!--第二种-->
    <ul>
        <li v-for="(item, index) in programLanguage">{{index + 1}} - {{item}}</li>
    </ul>
</div>

<script src="./js/vue.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    const app = new Vue({
        el: "#app",
        data: {programLanguage: ['C', 'C++', 'Java', 'Python', 'Golang', 'JavaScript']}
    })
</script>
```

### 8.1.1  触发数组的响应式

```javascript
let array = ['楚门的世界', '美国队长', '让子弹飞', '霸王别姬', '教父']
array.push('安娜贝尔')  
array.pop()  // 删除数组最后一个元素
array.shift() // 删除数组第一个元素
array.unshift('星际穿越') // 在数组最前面添加一个元素
                         // 参数为 ...
array.splice() // 删除、插入、替换元素
               // 删除：splice(param1, param2)  param1: 删除的位置，param2: 删除几个元素
               // 替换：splice(param1, param2, ...)  param1: 替换的位置，param2: 替换几个，...: 替换的元素
               // 插入：splice(param1, 0, ...)  param1: 插入的位置，...: 插入的位置
array.sort()
array.reverse()
```

## 8.2  遍历对象

```html
<div id="app">
    <!--第一种：只获取值-->
    <ul>
        <li v-for="value in info">{{value}}</li>
    </ul>
    <!--第二种：获取键与值-->
    <ul>
        <li v-for="(key, value) in info">{{key}} - {{value}}</li>
    </ul>
	<!--第三种：获取键、值与索引-->
    <ul>
        <li v-for="(key, value, index) in info">{{index + 1}} - {{key}} - {{value}}</li>
    </ul>
</div>

<script src="./js/vue.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    const app = new Vue({
        el: "#app",
        data: {
            info: {
                name: "lavau",
                age: 20,
                college: "computer and technology"
            }
        }
    })
</script>
```

## 8.3  key 的使用

为每个遍历项提供一个 ```key``` ，提高更新虚拟 DOM 的效率。



# 9.  JavaSrcipt 高阶补充——函数式编程

* filter() 

* map()

* reduce() 

  对数组中的所有内容进行汇总

```javascript
// 要求：找出数组中小于 100 的数，将它们逐个扩大二倍后，再求出它们的总和
let nums = [10, 20, 111, 222, 444, 40, 50];
let totalSum = nums.filter(function(n) {
    return n < 100;
}).map(function(n) {
    return n * 2;
}).reduce(function(perviousValue, n) {
    return perviousValue + n;
}, 0);

// 可以用箭头函数简化为
// let totalSum = nums.filter(n => n < 100).map(n => n * 2).reduce((perviousValue, n) => perviousValue + n, 0);
```



# 10.  表单绑定 v-model

 通过 v-model 实现数据间的双向绑定。

```html
<div id="app">
    <input type="text" v-model="message">
    <h2>{{message}}</h2>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {message: "你好"}
    })
</script>
```

## 10.1 原理

* v-bind
* v-on

```html
<div id="app">
    <!--<input type="text" v-bind:value="message" v-on:input="message = $event.target.value">-->
    <input type="text" :value="message" @input="message = $event.target.value">
    <h2>{{message}}</h2>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {message: "你好"}
    })
</script>
```

## 10.2  v-model 结合 radio

复习：单选框

``` html
<input type="radio" name="sex" value="male">男
<input type="radio" name="sex" value="female">女
```

```html
<div id="app">
			<label for="male"><input type="radio" id="male" value="男" name="sex" v-model="sex">男</label>
			<label for="female"><input type="radio" id="female" value="女" name="sex" v-model="sex">女</label>
    <h2>您选的性别是：{{sex}}</h2>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {
            sex: '---'
        }
    })
</script>
```

## 10.3  v-model 结合 checkbox

```html
<div id="app">
    <!--单选框-->
    <label for="agree">
        <input type="checkbox" id="agree" v-model="isAgree">同意协议
    </label><br>
    <button :disabled="!isAgree">下一步</button><br>

    <hr>

    <!--多选框-->
    <input type="checkbox" value="篮球" v-model="hobbies">篮球
    <input type="checkbox" value="足球" v-model="hobbies">足球
    <input type="checkbox" value="乒乓球" v-model="hobbies">乒乓球
    <input type="checkbox" value="羽毛球" v-model="hobbies">羽毛球
    <h3>您的爱好是 {{hobbies}}</h3>
</div>

<script src="./js/vue.min.js"></script>
<script>
    const app = new Vue({
        el: "#app",
        data: {
            isAgree: false,
            hobbies: []
        }
    })
</script>
```

## 10.4 v-model 结合 select

```html
<div id="app">
    <!--选择一个值-->
    <select v-model="mySelect">
        <option value="apple">苹果</option>
        <option value="orange">橘子</option>
        <option value="banana">香蕉</option>
    </select>
    <p>您最喜欢的水果: {{mySelect}}</p>

    <hr>

    <!--选择多个值-->
	<select v-model="mySelects" multiple>
		<option value="apple">苹果</option>
        <option value="orange">橘子</option>
        <option value="banana">香蕉</option>
    </select>
	<p>您喜欢的水果:{imySelects}}</p>
</div>

<script src="./js/vue.min.js"></script>
<script>
    let app = new Vue({
        el: '#app',
        data: {
        	mySelect: 'apple',
            mySelects: []
        }
    })
</script>
```

## 10.5  v-model 的修饰符

### 10.5.1  lazy

```v-model``` 默认在```input``` 事件中同步输入的数据。

而 ```lazy``` 修饰符让数据在输入框失去焦点时或回车时更新

### 10.5.2  number

Vue 默认把输入的内容当作字符串处理。

但如果输入为数字，就要加一个 ```number``` 

### 10.5.3  trim

去除内容首尾两边的空格

```html
<div id="app">
    <input type="text" v-model.lazy="message">
    <p>当前内容:{{message}}</p>
    
    年龄:<input type="number" v-model.number="age">
    <p>年龄:{fage}} 类型:{{typeof age}}</p>
    
    <input type="text" v-model.trim="message">
    <p>当前内容:----{imessage}}----</p>
</div>

```

