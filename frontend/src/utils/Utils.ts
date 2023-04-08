export function isInvalidRageNumber(target: number, min: number, max: number) {
	return target < min || target > max;
}

export function toWon(num: number) {
	return addComma(num) + '원';
}

export function addComma(num: number) {
	return num.toLocaleString('ko-KR');
}

export function hasBlank(str: string) {
	return str.match(/\s/g) || str === "";
}

export function isEndStr(target: string, endStr: string) {
	const REGEX = new RegExp(endStr + '$')
	return target.match(REGEX);
}

export function isValidStrLen(str: string, min: number, max: number) {
	const REGEX = '^.{' + min + ',' + max + '}$';
	return str.match(REGEX);
}

export function hasForbiddenStr(target: string, forbiddenStr: string) {
	const REGEX = new RegExp(forbiddenStr)
	return target.match(REGEX);
}