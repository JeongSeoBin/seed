#!/bin/sh

# 변수선언
_image_name="server2_spring_was_seed"
_nexus3_repo="10.253.12.87:12000"
_version=$1

echo _image_name = "${_image_name}"
echo _nexus3_repo = "${_nexus3_repo}"
echo _version = "${_version}"

# docker build
docker build -t "${_image_name}" .

# Image에 tag작성 ex) nexus저장소주소/image이름:버전
docker tag "${_image_name}" "${_nexus3_repo}"/"${_image_name}":"${_version}"
docker tag "${_image_name}" "${_nexus3_repo}"/"${_image_name}":latest

# Tag된 image를 nexus저장소로 push 한다.
docker push "${_nexus3_repo}"/"${_image_name}":"${_version}"
docker push "${_nexus3_repo}"/"${_image_name}":latest
