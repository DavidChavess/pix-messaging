import http from 'k6/http'
import { check, sleep } from 'k6'

export const options = {
  discardResponseBodies: true,
  scenarios: {
    contacts: {
      executor: 'per-vu-iterations',
      vus: 100,
      iterations: 200,
      maxDuration: '60s',
    },
  },
};

export default function () {
  const data = {
    id: `correlationId(${Math.random() * 1000000})`,
    targetKey: '123',
    sourceKey: '321',
    value: `${Math.random() * 1000}`
  }

  const params = {
    headers: {
      'Content-Type': 'application/json'
    }
  }

  const res = http.post('http://localhost:8080/pix',  JSON.stringify(data), params)

  check(res, { 'Success pix': (r) => r.status === 201 })

  sleep(0.3)
}
