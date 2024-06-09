import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1s', target: 100},
    { duration: '2s', target: 200},
    { duration: '3s', target: 300},
    // 추가 단계 작성 가능
    { duration: '10s', target: 100 },
  ],
};

export default function () {
  const url = 'http://localhost:8500/api/concerts/1'; // 요청을 보낼 엔드포인트 URL

  // 매 20초마다 요청 보내기
  if (__ITER % 20 === 0) {
    const res = http.get(url);
    check(res, {
      'status was 200': (r) => r.status === 200,
    });
  }

  sleep(1); // 1초 동안 휴식, 이 부분은 필요에 따라 조정 가능
}

