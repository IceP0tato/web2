// export된 자료 가져오기
import add from './math.js';
console.log(add(3, 6));

import config from './config.js';
console.log(config);

// default가 아닌 자료는 중괄호로 묶어야 함
import hello, {PI, E} from './util.js';
console.log(hello("유재석"));
console.log(PI);
console.log(E);

