# Vue 3 + TypeScript + Vite

vue3.x + typescript4.x + vite2.x + pinia2.x


## 集成配置

#### 为保证 node 的使用

```txt
npm i @types/node --save-dev
```

#### 集成 eslint

```txt
npm i eslint eslint-plugin-vue --save-dev

npm install @typescript-eslint/parser --save-dev

npm install @typescript-eslint/eslint-plugin --save-dev
```

#### 集成 prettier

```txt
npm i prettier eslint-config-prettier eslint-plugin-prettier --save-dev
```

#### 集成 pinia

```txt
npm i pinia --save
```

#### 集成 vue-router4

```txt
npm i vue-router --save
```

#### 集成 vueuse

```txt
npm i @vueuse/core
```

#### CSS 的集成 (scss)

```txt
npm add -D sass
```

#### 集成 axios

```txt
npm i axios
```

#### element-plus

#### 安装 husky

```txt
# 1.安装
npm i husky lint-staged -D

# 2.生成 .husky 的文件夹
npx husky install

# 3.添加 hooks，会在 .husky 目录下生成一个 pre-commit 脚本文件
npx husky add .husky/pre-commit "npx --no-install lint-staged"

# 4.添加 commit-msg
npx husky add .husky/commit-msg 'npx --no-install commitlint --edit "$1"'

# 5. 使用 `git commit -m "message"` 就会看到 hook 生效了。

作者：易师傅
链接：https://juejin.cn/post/7079785777692934174
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```