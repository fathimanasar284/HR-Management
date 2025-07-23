window.HR = window.HR || {};

HR.getUser = function() {
  try { return JSON.parse(localStorage.getItem('hr_user')); }
  catch { return null; }
};

HR.requireLogin = function() {
  const u = HR.getUser();
  if (!u || !u.success) {
    window.location.href = "/pages/login.html";
  }
  return u;
};

HR.hasRole = function(...roles) {
  const u = HR.getUser();
  if (!u) return false;
  return roles.includes(u.role);
};
